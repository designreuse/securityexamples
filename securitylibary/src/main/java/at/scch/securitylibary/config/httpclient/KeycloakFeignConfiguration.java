package at.scch.securitylibary.config.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration //Apply this configuration to all feign clients
public class KeycloakFeignConfiguration{
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Autowired
	AccessTokenResolver accessTokenResolver;
	
	@Bean
	public RequestInterceptor keycloakInterceptor() {
		return new RequestInterceptor() {

			@Override
			public void apply(RequestTemplate template) {
				String token = accessTokenResolver.getAccessTokenOfService();
				if(token!=null) {
					template.header(AUTHORIZATION_HEADER, "Bearer " + token);
				}
			}
			
		};
	}

}
