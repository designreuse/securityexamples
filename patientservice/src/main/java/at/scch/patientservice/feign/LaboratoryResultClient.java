package at.scch.patientservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "laboratory-service", configuration = KeycloakFeignConfiguration.class) //Custom configuration
@FeignClient(name = "laboratory-service")
public interface LaboratoryResultClient {
	
	@RequestMapping(value = "api/laboratory-results", method = RequestMethod.GET)
	Object[] getLaboratoryResults(@RequestParam("patientsvnr") Long patientsvnr);
	
	@RequestMapping(value = "api/laboratory-results/{id}", method = RequestMethod.GET)
	Object getLaboratoryResult(@PathVariable("id") Long id);

}
