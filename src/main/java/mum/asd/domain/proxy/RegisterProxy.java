package mum.asd.domain.proxy;

import java.util.List;

import mum.asd.controller.ApplicationController;
import mum.asd.domain.HotelUser;
import mum.asd.service.HotelUserService;


public class RegisterProxy extends ApplicationController  {
	private HotelUserService hotelUserService; 
	
	public void save(HotelUser entity) {
		// Proxy - Validate before save - Tan Tho Nguyen
		if(validate("Email", entity.getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
    	   validate("First Name", entity.getFirstName(), "[a-zA-Z]+") &&
    	   validate("Last Name", entity.getLastName(), "[a-zA-Z]+") && 
    	   emptyValidation("Password", entity.getPassword().isEmpty()) &&
    	   emptyValidation("Confirm Password", entity.getConfirmPassword().isEmpty()) &&
    	   validatePassword(entity.getPassword(), entity.getConfirmPassword())
    	   ){ 
			hotelUserService.save(entity); 
		}
	}
	
}
