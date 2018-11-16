package at.scch.securitylibary.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class CustomAuthoritiesExtractor implements AuthoritiesExtractor{
	
	@Value("${auth.client-id}")
	String clientId;
	
	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		return new ArrayList<GrantedAuthority>(extractKeycloakAuthorities(map));
	}
	
	@SuppressWarnings("unchecked")
	private Set<GrantedAuthority> extractKeycloakAuthorities(Map<String, Object> map) {
		
		List<String> roles = new ArrayList<>();

		Map<String, Object> realmMap = (Map<String, Object>) map.get("realm_access");
		if(!CollectionUtils.isEmpty(realmMap) && realmMap.containsKey("roles")) {
			roles.addAll((List<String>) realmMap.get("roles"));
		}

		Map<String, Object> resourceMap = (Map<String, Object>) map.get("resource_access");
		if(!CollectionUtils.isEmpty(resourceMap) && resourceMap.containsKey(clientId)) {
			Map<String, Map<String, Object>> clientResource = (Map<String, Map<String, Object>>) resourceMap.get(clientId);
			if(!CollectionUtils.isEmpty(clientResource) && clientResource.containsKey("roles")) {
				roles.addAll((List<String>) clientResource.get("roles"));
			}
		}
		
		SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
		mapper.setConvertToUpperCase(true);
		
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles.toArray(new String[0]));

		return mapper.mapAuthorities(authorities);
	}

}
