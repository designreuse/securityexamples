package at.scch.securitylibary.config.httpclient;

import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
	
	@Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RestTemplate keycloakRestTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory, LoadBalancerInterceptor interceptor) {
  		KeycloakRestTemplate result = new KeycloakRestTemplate(
  	            keycloakClientRequestFactory);
  	        // Add the interceptor for load balancing
  	        result.getInterceptors().add(interceptor);
  	        return result;
    }

}
