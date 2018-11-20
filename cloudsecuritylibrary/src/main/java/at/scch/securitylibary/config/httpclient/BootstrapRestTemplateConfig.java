package at.scch.securitylibary.config.httpclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class BootstrapRestTemplateConfig {
	
	@Bean
	public OAuth2RestTemplate serviceRestTemplate() {
		return new OAuth2RestTemplate(oAuthDetails());
	}	
	
    @Bean
    @ConfigurationProperties("auth")
    protected ClientCredentialsResourceDetails oAuthDetails() {
        return new ClientCredentialsResourceDetails();
    }

}
