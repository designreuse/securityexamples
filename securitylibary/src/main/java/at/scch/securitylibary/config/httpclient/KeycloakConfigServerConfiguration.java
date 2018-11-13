package at.scch.securitylibary.config.httpclient;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.client.RestTemplate;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class KeycloakConfigServerConfiguration {
	
	private String bearerToken;
	
	@Autowired
	private ConfigurableEnvironment environment;
	
	@Autowired
	private AccessTokenResolver accessTokenResolver;
	
	@PostConstruct
	public void init() {
		bearerToken = accessTokenResolver.getAccessTokenOfService();
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

}
