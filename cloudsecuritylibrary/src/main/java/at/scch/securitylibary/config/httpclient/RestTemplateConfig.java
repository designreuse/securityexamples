package at.scch.securitylibary.config.httpclient;

import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class RestTemplateConfig {
	
	@Bean
	@Primary
	public OAuth2RestTemplate restTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context, LoadBalancerInterceptor interceptor) {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, context);
		restTemplate.getInterceptors().add(interceptor);
		return restTemplate;
	}
	
	@Bean
	@RequestScope
	RestTemplate simpleRestTemplate(LoadBalancerInterceptor interceptor) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(interceptor);
		return restTemplate;
	}

}
