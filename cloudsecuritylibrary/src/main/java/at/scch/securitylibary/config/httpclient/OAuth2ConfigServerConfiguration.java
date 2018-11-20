package at.scch.securitylibary.config.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
@ConditionalOnMissingClass("org.springframework.cloud.config.server.ConfigServerApplication")
public class OAuth2ConfigServerConfiguration {
	
	@Autowired
	private ConfigurableEnvironment environment;
	
	@Autowired
	@Qualifier("serviceRestTemplate")
	OAuth2RestTemplate restTemplate;
	
	@Bean
	@Primary
	@ConditionalOnMissingClass("org.springframework.cloud.config.server.ConfigServerApplication")
    public ConfigServicePropertySourceLocator configServicePropertySourceLocator() {
		ConfigClientProperties clientProperties = configClientProperties();
		ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(
				clientProperties);
		configServicePropertySourceLocator.setRestTemplate(restTemplate);
		return configServicePropertySourceLocator;
				
	}
	
	@Bean
	@ConditionalOnMissingClass("org.springframework.cloud.config.server.ConfigServerApplication")
	public ConfigClientProperties configClientProperties() {
		ConfigClientProperties client = new ConfigClientProperties(this.environment);
		client.setEnabled(false);

		return client;
	}

}
