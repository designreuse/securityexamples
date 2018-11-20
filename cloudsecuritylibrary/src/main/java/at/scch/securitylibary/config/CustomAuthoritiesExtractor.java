package at.scch.securitylibary.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthoritiesExtractor implements AuthoritiesExtractor{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		List<String> roles = new ArrayList<>();
		if(map.containsKey("roles")) {
			roles.addAll((Collection<? extends String>) map.get("roles"));
		}
		if(map.containsKey("authorities")) {
			roles.addAll((Collection<? extends String>) map.get("authorities"));
		}
		
		SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
		mapper.setConvertToUpperCase(true);
		mapper.setPrefix("");
		
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles.toArray(new String[0]));

		return  new ArrayList<GrantedAuthority>(mapper.mapAuthorities(authorities));
	}

}
