package com.seviceBookingBackendapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seviceBookingBackendapp.dao.CompanyRepo;
import com.seviceBookingBackendapp.dto.ServicesDto;
import com.seviceBookingBackendapp.modal.Company;
import com.seviceBookingBackendapp.modal.Services;
import com.seviceBookingBackendapp.service.CompanyService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/Application")
public class CompanyController 
{
	@Autowired
	private CompanyRepo repo;
	
	@Autowired
	private CompanyService compService;
	
	@PostMapping("/newService")
	public String addServices(HttpSession session,@RequestBody Services obj)
	{
        Company curCompany = (Company) session.getAttribute("user");
        
        if(curCompany == null )
        	{
        		System.out.println("nothing found");
        		return "no one is logeed in";
        	}
        Services service = new Services(obj.getName(),obj.getPrices(),obj.getDescription());
        
        service.setCompany(curCompany); 
        
		compService.add(service);
        
		return "services added successfully";
	}
	
	@GetMapping("/allservices")
	public List<ServicesDto> getServ(HttpSession session)
	{
		Company curCompany = (Company) session.getAttribute("user");
		return  compService.getServ(curCompany);
	}
	
	
	
	
}
