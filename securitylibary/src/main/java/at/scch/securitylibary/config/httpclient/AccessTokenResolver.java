package at.scch.securitylibary.config.httpclient;

import java.util.Map;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AccessTokenResolver {
	
	protected String getAccessTokenOfUser() {
		KeycloakSecurityContext context = getKeycloakSecurityContext();
		if(context!=null) {
			return context.getTokenString();
		}
		return null;
	}
	
    @Value("${auth.client-id}")
    private String clientId;
    @Value("${auth.client-secret}")
    private String clientSecret;
    @Value("${auth.access-token-uri}")
    private String authEndpoint;
	
	@SuppressWarnings("rawtypes")
	protected String getAccessTokenOfService() {
		
		/*ClientRegistration cr = ClientRegistration.withRegistrationId("service-account")
			.tokenUri(authEndpoint)
			.clientId(clientId)
			.clientSecret(clientSecret)
			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
			.build();
		
		DefaultClientCredentialsTokenResponseClient client = new DefaultClientCredentialsTokenResponseClient();
		OAuth2AccessTokenResponse re = client.getTokenResponse(new OAuth2ClientCredentialsGrantRequest(cr));
		return re.getAccessToken().getTokenValue();*/
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(clientId, clientSecret));
		
		ResponseEntity<Map> response = restTemplate.postForEntity( authEndpoint, request , Map.class );
		return response.getBody().get("access_token").toString();
	}
	
    /**
     * Returns the {@link KeycloakSecurityContext} from the Spring {@link SecurityContextHolder}'s {@link Authentication}.
     *
     * @return the current <code>KeycloakSecurityContext</code>
     */
	protected KeycloakSecurityContext getKeycloakSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken token;
        KeycloakSecurityContext context;

        if (authentication == null) {
            return null;
        }

        if (!KeycloakAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
        	return null;
        }

        token = (KeycloakAuthenticationToken) authentication;
        context = token.getAccount().getKeycloakSecurityContext();

        return context;
    }


}
