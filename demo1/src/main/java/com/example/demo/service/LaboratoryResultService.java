package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.entity.LaboratoryResult;

public interface LaboratoryResultService {
	
	@PreAuthorize("hasAuthority('ROLE_READ_LABORITY_RESULT')")
	//@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	Collection<LaboratoryResult> getAllResults();

}
