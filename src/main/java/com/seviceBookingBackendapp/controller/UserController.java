package com.seviceBookingBackendapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seviceBookingBackendapp.dto.ServicesDto;
import com.seviceBookingBackendapp.modal.Company;
import com.seviceBookingBackendapp.modal.OrderDetails;
import com.seviceBookingBackendapp.modal.User;
import com.seviceBookingBackendapp.service.OrderSerice;
import com.seviceBookingBackendapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/Users")
public class UserController 	
{
	@Autowired
	private UserService userServ;
	
	@Autowired
	private OrderSerice orderServ;
	
	@GetMapping("/services/{name}")
	public List<ServicesDto> searchServices(@PathVariable String name )
	{
		return userServ.searchServices(name);
	}
	
	@PostMapping("/boot")
	public void book(HttpSession session,@RequestBody OrderDetails obj)
	{
		User user = (User) session.getAttribute("user");
		obj.setEmailId(user.getEmailId());
		orderServ.book(obj);
	}
	
	
	
}
