package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LaboratoryResult;

@Service
public class LaboratoryResultServiceImpl implements LaboratoryResultService {
	
	private List<LaboratoryResult> laboratoryResults;
	
	@Autowired
	public LaboratoryResultServiceImpl(UserService userService) {
		laboratoryResults = new ArrayList<>();
		laboratoryResults.add(new LaboratoryResult(1L, 123L, "Test1", true, userService.loadUserByUsername("patient1")));
		laboratoryResults.add(new LaboratoryResult(2L, 321L, "Test2", false, userService.loadUserByUsername("patient1")));
		laboratoryResults.add(new LaboratoryResult(3L, 132L, "Test3", true, userService.loadUserByUsername("patient2")));
		laboratoryResults.add(new LaboratoryResult(4L, 312L, "Test4", false, userService.loadUserByUsername("patient3")));
	}

	@Override
	public Collection<LaboratoryResult> getAllResults() {
		return laboratoryResults;
	}

}
