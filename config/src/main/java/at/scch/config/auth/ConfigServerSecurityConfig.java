package at.scch.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import at.scch.securitylibary.config.KeycloakConfig;

@Configuration
public class ConfigServerSecurityConfig extends KeycloakConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	super.configure(http);
        http.authorizeRequests().anyRequest().hasAnyAuthority("ROLE_ADMIN", "ROLE_SYSTEM")
        .and().csrf().disable();
        
        http.logout().logoutSuccessUrl("/logout-success");
    }
}
