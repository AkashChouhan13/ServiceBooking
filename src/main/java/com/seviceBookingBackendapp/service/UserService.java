package com.seviceBookingBackendapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seviceBookingBackendapp.dao.ServiceDao;
import com.seviceBookingBackendapp.dto.ServicesDto;
import com.seviceBookingBackendapp.modal.Services;

@Service
public class UserService 
{
	@Autowired
	ServiceDao servDao;
	
	public List<ServicesDto> searchServices(String servName) 
	{
		List<Services>  list = servDao.findAllByName(servName);
		

    	
    	List<ServicesDto> ans = new ArrayList<>();
    	
    	 if (list.isEmpty())
    	 {
    		 ServicesDto servDto = new ServicesDto();
    		 servDto.setName("No services are available right now");
    		 servDto.setDescription("hope you will get this service soon");
    		 ans.add(servDto);
 	     }
    	 else
    	 {
 	        for (Services services : list)
 	        {	
 	        	ServicesDto servDto = new ServicesDto();
 	        	//servDto.setId(services.getCompany().getCompId());
 	        	servDto.setCompanyName(services.getCompany().getCompName());
 	        	servDto.setName(services.getName());
 	        	servDto.setDescription(services.getDescription());
 	        	servDto.setPrices(services.getPrices());
 	        	
 	        	ans.add(servDto);
 	        	
 	        }
 	        
 	    }
    	 return ans;
    	
		
	}

}
