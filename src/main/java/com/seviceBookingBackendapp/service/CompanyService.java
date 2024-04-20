package com.seviceBookingBackendapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seviceBookingBackendapp.dao.CompanyRepo;
import com.seviceBookingBackendapp.dao.ServiceDao;
import com.seviceBookingBackendapp.dto.ServicesDto;
import com.seviceBookingBackendapp.modal.Company;
import com.seviceBookingBackendapp.modal.Services;

@Service
public class CompanyService 
{
 
    @Autowired
    private ServiceDao servDao;
	
    @Autowired
    private CompanyRepo compDao;
    
    public String add( Services service)
    {
    	servDao.save(service);
    	
    	return "Added Successfully";
    			
    }
    
    public List<ServicesDto> getServ(Company comp)
    {
    	List<Services>  list = comp.getServices();
    	

    	
    	List<ServicesDto> ans = new ArrayList<>();
    	
    	 if (list.isEmpty())
    	 {
    		 ServicesDto servDto = new ServicesDto();
    		 servDto.setName("No services are available right now");
    		 ans.add(servDto);
 	     }
    	 else
    	 {
 	        for (Services services : list)
 	        {	
 	        	ServicesDto servDto = new ServicesDto();
 	        	
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
