package at.scch.laboratoryservice.entity;

import com.fasterxml.jackson.annotation.JsonView;

import at.scch.securitylibary.config.jsonview.SecuredEntityViewPolicy;
import at.scch.securitylibary.config.jsonview.Views;

@SecuredEntityViewPolicy(value = "MedicalViewPolicy")
public class LaboratoryResult {
	
	private Long id;
	private Long valueA;
	private Long valueB;
	
	@JsonView(Views.Extended.class)
	private String valueC;
	@JsonView(Views.Extended.class)
	private Boolean valueD;
	private Long patientSvnr;
	
	public LaboratoryResult(Long id, Long valueA, Long valueB, String valueC, Boolean valueD, Long patientSvnr) {
		super();
		this.id = id;
		this.valueA = valueA;
		this.setValueB(valueB);
		this.valueC = valueC;
		this.valueD = valueD;
		this.setPatientSvnr(patientSvnr);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValueB() {
		return valueB;
	}

	public void setValueB(Long valueB) {
		this.valueB = valueB;
	}

	public Long getPatientSvnr() {
		return patientSvnr;
	}

	public void setPatientSvnr(Long patientSvnr) {
		this.patientSvnr = patientSvnr;
	}



}
