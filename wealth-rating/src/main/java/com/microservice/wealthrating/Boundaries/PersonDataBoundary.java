package com.microservice.wealthrating.Boundaries;


//This boundary will be used for "/handle" request from client
public class PersonDataBoundary {

	private Long id;
	private personalInfoBoundary personalInfo;
	private financialInfoBoundary financialInfo;
	
	public PersonDataBoundary() {
		this.personalInfo = new personalInfoBoundary();
		this.financialInfo = new financialInfoBoundary();
	}
	
	public PersonDataBoundary(Long id) {
		this.id = id;
		this.personalInfo = new personalInfoBoundary();
		this.financialInfo = new financialInfoBoundary();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
		
	}
	public personalInfoBoundary getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(personalInfoBoundary personalInfo) {
		this.personalInfo = personalInfo;
	}
	public financialInfoBoundary getFinancialInfo() {
		return financialInfo;
	}
	public void setFinancialInfo(financialInfoBoundary financialInfo) {
		this.financialInfo = financialInfo;
	}
	
	

}
