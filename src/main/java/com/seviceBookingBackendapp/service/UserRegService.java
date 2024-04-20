package com.seviceBookingBackendapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.seviceBookingBackendapp.dao.UserRepo;
import com.seviceBookingBackendapp.email.Email;
import com.seviceBookingBackendapp.modal.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserRegService 
{
	@Autowired
	private UserRepo repo;
	
	@Autowired
	Email emailobj;
	
	private String getApplicationBaseUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURL().toString();
        String contextPath = request.getContextPath();

        // Remove the request URI to get the base URL
        return requestUrl.replace(request.getRequestURI(), contextPath);
    }

	
	
	
	public String register(User obj)
	{
		Optional<User> optional = repo.findByEmailId(obj.getEmailId()) ;
		
		User user;
		if(optional.isPresent())
		{
			user = optional.get();
			if(user.isEnable() == true)
			return "user is already preset";
			else
				repo.delete(user);

			
			
		}
		
			
			String url = getApplicationBaseUrl();
			
			int val = (int) (Math.random() * 9000) + 1000;
			String token = "" + val;

			url = url + "/userlogin/verifyRegistration?token=" + token + "&email=" + obj.getEmailId();

			
			obj.setOtp(token) ;
			repo.save(obj);
			
			emailobj.sendSimpleMail(obj.getEmailId(),url);
			
			
			return "confirmation link sent via mail";
		
	}
		

		public String validateToken(String token,String email)
		{
			Optional<User> optional = repo.findByEmailId(email) ;
			User user = null;
			
			System.out.println(token);
			
			if(optional.isPresent())
				user =  optional.get();
			else return "invalid";
				
			System.out.println(user.getOtp());
			if( user.getOtp().equals(token))
			{
				user.setEnable(true);
				repo.save(user);
				return "registration successfull";
			}
			
			return "registration failed";
				
		}
		
		
	
		public String login(HttpSession session,User loginObj)
		{
			Optional<User> optional = repo.findByEmailId(loginObj.getEmailId());
			User user = null;
			
			if(optional.isPresent())
			{
				user = optional.get();
				if(user.isEnable() == false)return "please register first";

			}
			else 
			{
				return "User with this email not found please register !" ;
			}
			
			
			if(!user.getPassword().equals(loginObj.getPassword() ))
			{
				return "password not matched";
			}
			else
			{
				session.setAttribute("user", user);
				return "login sucessful";
			}
		
			
		}


	
}
