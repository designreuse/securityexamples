package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LaboratoryResultService;

import com.example.demo.entity.LaboratoryResult;

@RestController
public class LaboratoryResultController {
	
	@Autowired
	LaboratoryResultService laboratoryResultService;
	
	@RequestMapping(value = "api/laboratory-results", method = RequestMethod.GET)
	Collection<LaboratoryResult> getAllResults(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return laboratoryResultService.getAllResults();
	}

}
