package at.scch.securitylibary.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

//@ControllerAdvice
public class JsonViewConfiguration extends AbstractMappingJacksonResponseBodyAdvice {
	
	@Autowired
	JsonViewCalculator jsonViewCalculator;
	
	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

		Class<?> viewClass = bodyContainer.getSerializationView();
		if (viewClass != null) {
			return;
		}

		viewClass = jsonViewCalculator.getJSONView(bodyContainer.getValue());
		if(viewClass !=null) {
			bodyContainer.setSerializationView(viewClass);
			return;
		}
		
		viewClass = Views.Anonymous.class;
		
		//old static mapping of Roles to Views
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null) {
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities();

			if (authorities.stream().anyMatch(o -> o.getAuthority().equals("ROLE_ADMIN"))) {
				viewClass = Views.Admin.class;
			} else if (authorities.stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))) {
				viewClass = Views.Simple.class;
			}
		}
		
		bodyContainer.setSerializationView(viewClass);

	}
}