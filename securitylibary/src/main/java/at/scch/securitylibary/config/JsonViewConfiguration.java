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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@ControllerAdvice
public class JsonViewConfiguration extends AbstractMappingJacksonResponseBodyAdvice {
	
	@Autowired(required = false)
	JsonViewCalculator jsonViewCalculator;
	
	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
		
		//Check if JSON View already set in Controller
		Class<?> viewClass = bodyContainer.getSerializationView();
		if (viewClass != null) {
			return;
		}
		
		//Apply JSON View Policy if available
		if(jsonViewCalculator!=null) {
			viewClass = jsonViewCalculator.getJsonView(bodyContainer.getValue());
			if(viewClass !=null) {
				bodyContainer.setSerializationView(viewClass);
				return;
			}
		}
		
		//Otherwise use static mapping
		viewClass = Views.Anonymous.class;
		
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