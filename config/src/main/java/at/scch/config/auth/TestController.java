package at.scch.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "test";
	}

}
