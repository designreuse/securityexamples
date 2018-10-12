package com.example.demo.entity;

import com.example.demo.config.SecuredEntityViewPolicy;
import com.fasterxml.jackson.annotation.JsonView;

@SecuredEntityViewPolicy(value = "MedicalViewPolicy")
public class LaboratoryResult {
	
	private Long id;
	private Long valueA;
	
	@JsonView(View.Extended.class)
	private String valueC;
	private Boolean valueD;
	
	@JsonView(View.Detail.class)
	private User patient;
	
	public LaboratoryResult(Long id, Long valueA, String valueC, Boolean valueD, User patient) {
		super();
		this.id = id;
		this.valueA = valueA;
		this.valueC = valueC;
		this.valueD = valueD;
		this.patient = patient;
	}

	public LaboratoryResult() {
		super();
	}
	
	//Optional create customized view in entity class
	//interface SensibleDataView extends View.Detail{}

	public Long getValueA() {
		return valueA;
	}

	public void setValueA(Long valueA) {
		this.valueA = valueA;
	}

	public String getValueC() {
		return valueC;
	}

	public void setValueC(String valueC) {
		this.valueC = valueC;
	}

	public Boolean getValueD() {
		return valueD;
	}

	public void setValueD(Boolean valueD) {
		this.valueD = valueD;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
