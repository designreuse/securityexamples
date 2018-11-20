package at.scch.securitylibary.config.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration //Apply this configuration to all feign clients
public class OAuth2FeignConfiguration{
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Autowired
	OAuth2RestTemplate restTemplate;
	
	@Bean
	public RequestInterceptor keycloakInterceptor() {
		return new RequestInterceptor() {

			@Override
			public void apply(RequestTemplate template) {
				String token = restTemplate.getAccessToken().getValue();
				if(token!=null) {
					template.header(AUTHORIZATION_HEADER, "Bearer " + token);
				}
			}
			
		};
	}

}
