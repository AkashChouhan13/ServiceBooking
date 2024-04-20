package com.seviceBookingBackendapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seviceBookingBackendapp.modal.User;
import com.seviceBookingBackendapp.service.UserRegService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/userlogin")
public class UserRegister 
{
	@Autowired
	private UserRegService serv;
	
	@PostMapping(value = "/register")
	public String register(@RequestBody User obj )
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
	public String login(HttpSession session, @RequestBody User loginObj)
	{
		return serv.login(session, loginObj);
	}
	
}
