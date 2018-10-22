package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.example.demo.config.authentication.AuthenticationArgumentResolver;

//@Configuration
public class WebConfig extends WebMvcConfigurationSupport  {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(createAuthenticationResolver());
	}

	@Bean
	public AuthenticationArgumentResolver createAuthenticationResolver() {
		return new AuthenticationArgumentResolver();
	}
}
