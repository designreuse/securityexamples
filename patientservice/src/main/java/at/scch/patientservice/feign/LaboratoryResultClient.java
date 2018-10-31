package at.scch.patientservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.scch.securitylibary.config.KeycloakFeignConfiguration;

@FeignClient(name = "laboratory-service", configuration = KeycloakFeignConfiguration.class)
public interface LaboratoryResultClient {
	
	@RequestMapping(value = "api/laboratory-results", method = RequestMethod.GET)
	Object[] getLaboratoryResults(@RequestParam("patientsvnr") Long patientsvnr);
	
	@RequestMapping(value = "api/laboratory-results/{id}", method = RequestMethod.GET)
	Object getLaboratoryResult(@PathVariable("id") Long id);

}
