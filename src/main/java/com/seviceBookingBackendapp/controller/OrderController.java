package com.seviceBookingBackendapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seviceBookingBackendapp.modal.Company;
import com.seviceBookingBackendapp.modal.OrderDetails;
import com.seviceBookingBackendapp.modal.User;
import com.seviceBookingBackendapp.service.OrderSerice;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController
{
	@Autowired
	private OrderSerice serv;
	
	@GetMapping("/viewOrder")
	public List<OrderDetails> viewOrder(HttpSession session)
	{
		Company curCompany = (Company) session.getAttribute("user");
		return serv.viewOrder(curCompany.getCompName());
	}
	
	@GetMapping("/cart")
	public List<OrderDetails> cart(HttpSession session)
	{
		User user = (User) session.getAttribute("user");
		return serv.cart(user.getEmailId());
		
	}
	
	@PutMapping("/update/{orderId}/{status}")
    public String  updateStatus(@PathVariable Integer orderId, @PathVariable String status)
	{
		return  serv.updateStatus(orderId, status);
        
    }
	
}
