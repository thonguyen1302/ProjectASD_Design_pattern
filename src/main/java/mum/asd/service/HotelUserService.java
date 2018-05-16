package mum.asd.service;

import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.generic.GenericService;

public interface HotelUserService extends GenericService<HotelUser> {

	boolean authenticate(String email, String password);
	
	User findByEmail(String email);
	
}
