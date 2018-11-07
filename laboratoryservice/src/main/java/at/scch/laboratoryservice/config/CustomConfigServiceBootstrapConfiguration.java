package at.scch.laboratoryservice.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
@EnableOAuth2Client
public class CustomConfigServiceBootstrapConfiguration {
	
	@Value("${spring.cloud.config.client.oauth2.client-id}")
	private String clientId;
	@Value("${spring.cloud.config.client.oauth2.client-secret}")
	private String clientSecret;
	@Value("${spring.cloud.config.client.oauth2.access-token-uri}")
	private String authEndpoint;
	
	private String bearerToken;
	
	@Autowired
	private ConfigurableEnvironment environment;
	
	@PostConstruct
	public void init() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(clientId, clientSecret));
		
		ResponseEntity<Map> response = restTemplate.postForEntity( authEndpoint, request , Map.class );
		bearerToken = response.getBody().get("access_token").toString();
	}
	
	@Bean
	@Primary
    public ConfigServicePropertySourceLocator configServicePropertySourceLocator() {
		ConfigClientProperties clientProperties = configClientProperties();
		ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(
				clientProperties);
		configServicePropertySourceLocator.setRestTemplate(customRestTemplate(clientProperties));
		return configServicePropertySourceLocator;
				
	}
	
	private RestTemplate customRestTemplate(ConfigClientProperties clientProperties) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + bearerToken);
		
		RestTemplate template = new RestTemplate();
		//template.setInterceptors(Arrays.<ClientHttpRequestInterceptor> asList(new GenericRequestHeaderInterceptor(headers)));
		template.getInterceptors().add(new GenericRequestHeaderInterceptor(headers));
		return template;
		
	}

	@Bean
	public ConfigClientProperties configClientProperties() {
		ConfigClientProperties client = new ConfigClientProperties(this.environment);
		client.setEnabled(false);

		return client;
	}
	
	public static class GenericRequestHeaderInterceptor implements ClientHttpRequestInterceptor {

		private final Map<String, String> headers;

		public GenericRequestHeaderInterceptor(Map<String, String> headers) {
			this.headers = headers;
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
				throws IOException {
			for (String header : headers.keySet()) {
				request.getHeaders().add(header, headers.get(header));
			}
			return execution.execute(request, body);
		}


	}


}
