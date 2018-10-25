package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.entity.LaboratoryResult;

public interface LaboratoryResultService {
	
	@PreAuthorize("hasAuthority('READ_LABORATORY_RESULT')")
	Collection<LaboratoryResult> getAllResults(Long patientSvnr);
	
	@PreAuthorize("hasAuthority('READ_LABORATORY_RESULT')")
	LaboratoryResult getResult(Long id);

}
