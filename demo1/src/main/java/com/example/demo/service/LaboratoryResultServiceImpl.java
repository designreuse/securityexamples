package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LaboratoryResult;

@Service
public class LaboratoryResultServiceImpl implements LaboratoryResultService {
	
	private List<LaboratoryResult> laboratoryResults;
	
	@Autowired
	public LaboratoryResultServiceImpl() {
		laboratoryResults = new ArrayList<>();
		laboratoryResults.add(new LaboratoryResult(1L, 123L, 456L, "Test1", true, 123401011990L));
		laboratoryResults.add(new LaboratoryResult(2L, 321L, 654L, "Test2", false, 123401011990L));
		laboratoryResults.add(new LaboratoryResult(3L, 132L, 465L, "Test3", true, 123501011990L));
		laboratoryResults.add(new LaboratoryResult(4L, 312L, 546L, "Test4", false, 123601011990L));
	}

	@Override
	public Collection<LaboratoryResult> getAllResults(Long patientSvnr) {
		if(patientSvnr==null) {
			return laboratoryResults;
		}
		List<LaboratoryResult> result = new ArrayList<>();
		for(LaboratoryResult i : laboratoryResults) {
			if(i.getPatientSvnr().equals(patientSvnr)) {
				result.add(i);
			}
		}
		return result;
	}

	@Override
	public LaboratoryResult getResult(Long id) {
		for(LaboratoryResult i : laboratoryResults) {
			if(i.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}

}
