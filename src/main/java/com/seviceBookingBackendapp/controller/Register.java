package com.seviceBookingBackendapp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seviceBookingBackendapp.modal.Company;
import com.seviceBookingBackendapp.service.RegistrationService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/login")
public class Register 
{
	@Autowired
	private RegistrationService serv;
	
	@PostMapping(value = "/register")
	public String register(@RequestBody Company obj )
	{
		if(obj == null)return "Ram Ram";
		
		return serv.register(obj);
	}
	
	
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token") String token,@RequestParam("email") String email)
	{
		String result = serv.validateToken(token, email);
		
		return result;
	}
	
	@GetMapping("/login")
	public String login(HttpSession session, @RequestBody Company loginObj)
	{
		return serv.login(session, loginObj);
	}
	
	
	@GetMapping("/comp")
	public String getComp(HttpSession session)
	{
		return serv.getComp(session);
	}
	
}