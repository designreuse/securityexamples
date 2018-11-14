package at.scch.securitylibary.config.httpclient;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

@Configuration
public class KeycloakEurekaConfiguration {
	
	@Autowired
	AccessTokenResolver tokenResolver;
	
	@Primary
	@Bean
	DiscoveryClientOptionalArgs test() {
		
		Collection<ClientFilter> filters = new ArrayList<>();
		filters.add(new ClientFilter() {

			@Override
			public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
				String token = tokenResolver.getAccessTokenOfService();
				if(token!=null) {
					cr.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
				}
				return getNext().handle(cr);
			}
			
		});
		
		DiscoveryClientOptionalArgs args = new DiscoveryClientOptionalArgs();
		args.setAdditionalFilters(filters);
		return args;
	}

}
