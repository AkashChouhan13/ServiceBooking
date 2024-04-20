package com.seviceBookingBackendapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seviceBookingBackendapp.dao.OrderDao;
import com.seviceBookingBackendapp.modal.OrderDetails;

@Service
public class OrderSerice 
{
	@Autowired
	OrderDao orderDao;
	
	public void book(OrderDetails obj) 
	{
		//obj -- compname,sname,	useremail
		orderDao.save(obj);
	}

	public List<OrderDetails> cart(String emailId) 
	{
		return orderDao.findByEmailId(emailId);
	}

	public List<OrderDetails> viewOrder(String compname) {
		// TODO Auto-generated method stub
		return orderDao.findByCompanyName(compname) ;
	}

	public String updateStatus(Integer orderId, String status) 
	{
		Optional<OrderDetails> obj = orderDao.findById(orderId);
		
		 if (obj.isPresent()) 
		 {
	            OrderDetails order = obj.get();
	            order.setStatus(status);
	            orderDao.save(order);
	            
	            return "status updated";
	     }
		
		 return "No such order exists";
		
	}
	
}
