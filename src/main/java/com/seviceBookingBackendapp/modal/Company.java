package com.seviceBookingBackendapp.modal;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class Company 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer compId;
	
	private String companyDis;
	private String compName; 
	private String compEmail;
	private String location;
	private String pass;
	private boolean isEnable;
	private String otp;
	private String compContact;
	
	public Company(Integer compId, String companyDis, String compName, String compEmail, String pass, boolean isEnable,
			String otp, String compContact) {
		super();
		this.compId = compId;
		this.companyDis = companyDis;
		this.compName = compName;
		this.compEmail = compEmail;
		this.pass = pass;
		this.isEnable = isEnable;
		this.otp = otp;
		this.compContact = compContact;
	}
	
	public Company() {}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public String getCompanyDis() {
		return companyDis;
	}

	public void setCompanyDis(String companyDis) {
		this.companyDis = companyDis;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getCompContact() {
		return compContact;
	}

	public void setCompContact(String compContact) {
		this.compContact = compContact;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

	
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER )
    @JsonIgnore
    private List<Services> services; 

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}

	
	
	
	
	
}
