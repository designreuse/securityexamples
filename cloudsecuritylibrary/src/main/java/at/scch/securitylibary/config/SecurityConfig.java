package at.scch.securitylibary.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
    	.cors()
    	.and().
    	authorizeRequests().anyRequest().permitAll();
    }
	
	@Bean
   	public WebMvcConfigurer corsConfigurer() {
   		return new WebMvcConfigurer() {
   			@Override
   			public void addCorsMappings(CorsRegistry registry) {
   				registry.addMapping("/**").allowedMethods("PUT", "DELETE", "GET", "OPTIONS", "POST", "PATCH");
   			}
   		};
   	} 

}
