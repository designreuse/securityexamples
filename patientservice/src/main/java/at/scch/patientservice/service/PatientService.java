package at.scch.patientservice.service;

import org.springframework.security.access.prepost.PreAuthorize;

import at.scch.patientservice.entity.Patient;

public interface PatientService {
	
	@PreAuthorize("hasAuthority('READ_PATIENT')")
	Iterable<Patient> getAllPatients();
	
	@PreAuthorize("hasAuthority('READ_PATIENT')")
	Patient getPatient(Long svnr);

}
