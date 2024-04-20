package com.seviceBookingBackendapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.seviceBookingBackendapp.dao.CompanyRepo;
import com.seviceBookingBackendapp.email.Email;
import com.seviceBookingBackendapp.modal.Company;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class RegistrationService 
{
	@Autowired
	private CompanyRepo repo;
	
	@Autowired
	Email emailobj;
	
	private String getApplicationBaseUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURL().toString();
        String contextPath = request.getContextPath();

        // Remove the request URI to get the base URL
        return requestUrl.replace(request.getRequestURI(), contextPath);
    }

	
	
	
	public String register(Company obj)
	{
		Optional<Company> optional = repo.findByCompEmail(obj.getCompEmail()) ;
		
		Company user;
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

			url = url + "/login/verifyRegistration?token=" + token + "&email=" + obj.getCompEmail();

			
			obj.setOtp(token) ;
			repo.save(obj);
			
			emailobj.sendSimpleMail(obj.getCompEmail(),url);
			
			
			return "confirmation link sent via mail";
		
	}
		

		public String validateToken(String token,String email)
		{
			Optional<Company> optional = repo.findByCompEmail(email) ;
			Company user = null;
			
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
		
		
	
		public String login(HttpSession session,Company loginObj)
		{
			Optional<Company> optional = repo.findByCompEmail(loginObj.getCompEmail());
			Company user = null;
			
			if(optional.isPresent())
			{
				user = optional.get();
				
			}
			else return "email not found";
			
			if(user.isEnable() == false)return "please register first";
			
			
			
			if(!user.getPass().equals(loginObj.getPass() ))
			{
				return "password not matched";
			}
			else
			{
				session.setAttribute("user", user);
				
				Company curCompany = (Company) session.getAttribute("user");
				System.out.println(curCompany.getCompName());
				return "login sucessful";
			}
		
			
		}
		
		public String getComp(HttpSession session)
		{
			Company curCompany = (Company) session.getAttribute("user");
			return curCompany.getCompName() ;
			
		}

}