package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.entity.LaboratoryResult;

public interface LaboratoryResultService {
	
	@PreAuthorize("hasAuthority('READ_LABORITORY_RESULTS')")
	Collection<LaboratoryResult> getAllResults();

}
