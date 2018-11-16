package at.scch.securitylibary.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OpenIDPrincipalExtractor implements PrincipalExtractor{
	
	@Autowired
	OAuth2RestTemplate template;

	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		 OAuth2AccessToken accessToken;
	        try {
	            accessToken = template.getAccessToken();
	        } catch (final OAuth2Exception e) {
	            throw new BadCredentialsException("Could not obtain access token", e);
	        }
	        try {
	            final String idToken = accessToken.getAdditionalInformation().get("id_token").toString();
	            String kid = JwtHelper.headers(idToken)
	                .get("kid");
	            //final Jwt tokenDecoded = JwtHelper.decodeAndVerify(idToken, verifier(kid));
	            final Jwt tokenDecoded = JwtHelper.decode(idToken);
	            final Map<String, String> authInfo = new ObjectMapper().readValue(tokenDecoded.getClaims(), Map.class);
	            //verifyClaims(authInfo);
	            //final OpenIdConnectUserDetails user = new OpenIdConnectUserDetails(authInfo, accessToken);
	            return authInfo;
	        } catch (final Exception e) {
	            throw new BadCredentialsException("Could not obtain user details from token", e);
	        }
	}

}
