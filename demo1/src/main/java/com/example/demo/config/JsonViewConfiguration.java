package com.example.demo.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.example.demo.entity.View;

@ControllerAdvice
public class JsonViewConfiguration extends AbstractMappingJacksonResponseBodyAdvice {
	
	
    /*public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return super.supports(returnType, converterType);
    }*/

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
		
			Class<?> viewClass = View.User.class;

	       /* if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null) {
	            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

	            if (authorities.stream().anyMatch(o -> o.getAuthority().equals(Role.GUEST.getValue()))) {
	                viewClass = View.Guest.class;
	            }
	            if (authorities.stream().anyMatch(o -> o.getAuthority().equals(Role.ORGANIZER.getValue()))) {
	                viewClass = View.Organizer.class;
	            }
	            if (authorities.stream().anyMatch(o -> o.getAuthority().equals(Role.BUSINESS_ADMIN.getValue()))) {
	                viewClass = View.BusinessAdmin.class;
	            }
	            if (authorities.stream().anyMatch(o -> o.getAuthority().equals(Role.TECHNICAL_ADMIN.getValue()))) {
	                viewClass = View.TechnicalAdmin.class;
	            }
	        }*/
	        bodyContainer.setSerializationView(viewClass);
		
	}
}