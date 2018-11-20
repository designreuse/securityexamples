package at.scch.securitylibary.config;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	 public ResourceServerConfiguration() {
         super();
     }
	 
	 @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {}

     @Override
     public void configure(HttpSecurity http) throws Exception {
    	 http
	         .requestMatcher(BearerTokenRequestMatcher.build())
	         .authorizeRequests()
	             .anyRequest().permitAll().and();
     }
     
     public static class BearerTokenRequestMatcher implements RequestMatcher {

         @Override
         public boolean matches(HttpServletRequest request) {
             return Optional.ofNullable(request.getHeader("Authorization"))
                 .map(value -> value.startsWith("Bearer"))
                 .orElse(false);
         }

         public static BearerTokenRequestMatcher build() {
             return new BearerTokenRequestMatcher();
         }
     }


     

}
