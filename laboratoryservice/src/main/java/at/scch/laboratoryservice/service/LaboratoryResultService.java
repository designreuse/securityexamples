package at.scch.laboratoryservice.service;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;

import at.scch.laboratoryservice.entity.LaboratoryResult;

public interface LaboratoryResultService {
	
	@PreAuthorize("hasAuthority('READ_LABORATORY_RESULT')")
	Collection<LaboratoryResult> getAllResults(Long patientSvnr);
	
	@PreAuthorize("hasAuthority('READ_LABORATORY_RESULT')")
	LaboratoryResult getResult(Long id);

}
