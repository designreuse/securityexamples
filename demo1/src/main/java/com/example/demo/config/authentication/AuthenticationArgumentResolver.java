package com.example.demo.config.authentication;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		if (supportsParameter(methodParameter)) {
			return createCustomAuthentication(webRequest);
		}
		else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}

	private Object createCustomAuthentication(NativeWebRequest webRequest) {
		if(webRequest==null || webRequest.getUserPrincipal()==null ||
				!(webRequest.getUserPrincipal() instanceof KeycloakAuthenticationToken)) {
			
			return new CustomAuthenticationEntity(null, null, null, null, false, null);
		}
			
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) webRequest.getUserPrincipal();
		KeycloakPrincipal<?> principal=(KeycloakPrincipal<?>)token.getPrincipal();
        AccessToken accessToken = principal.getKeycloakSecurityContext().getToken();

		return new CustomAuthenticationEntity(accessToken.getId(), accessToken.getGivenName(), accessToken.getFamilyName(), accessToken.getEmail(), true,
				accessToken.getRealmAccess().getRoles());
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterAnnotation(CurrentAuthentication.class) != null
				&& methodParameter.getParameterType().equals(CustomAuthenticationEntity.class);
	}

}
