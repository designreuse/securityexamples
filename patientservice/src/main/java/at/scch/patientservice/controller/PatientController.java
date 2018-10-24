package at.scch.patientservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import at.scch.patientservice.entity.Patient;
import at.scch.patientservice.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "/api/patients", method = RequestMethod.GET)
	public Iterable<Patient> getAllPatients(){
		return patientService.getAllPatients();
	}
	
	@RequestMapping(value = "/api/patients/{svnr}", method = RequestMethod.GET)
	public Patient gePatient(@PathVariable Long svnr){
		return patientService.getPatient(svnr);
	}

}
