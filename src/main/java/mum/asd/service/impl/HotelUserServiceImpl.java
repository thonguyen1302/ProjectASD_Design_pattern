package mum.asd.service.impl;

import java.util.List;

import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.repository.HotelUserRepository;
import mum.asd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.asd.service.HotelUserService;
import mum.asd.service.UserService;

@Service
public class HotelUserServiceImpl implements HotelUserService {
	
	@Autowired
	private HotelUserRepository hotelUserRepository;
	
	@Override
	public HotelUser save(HotelUser entity) {
		return hotelUserRepository.save(entity);
	}

	@Override
	public HotelUser update(HotelUser entity) {
		return hotelUserRepository.save(entity);
	}

	@Override
	public void delete(HotelUser entity) {
		hotelUserRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		hotelUserRepository.delete(id);
	}

	@Override
	public HotelUser find(Long id) {
		return hotelUserRepository.findOne(id);
	}

	@Override
	public List<HotelUser> findAll() {
		return hotelUserRepository.findAll();
	}

	@Override
	public boolean authenticate(String username, String password){
		HotelUser user = this.findByEmail(username);
		if(user == null){
			return false;
		}else{
			if(password.equals(user.getPassword())) return true;
			else return false;
		}
	}

	@Override
	public HotelUser findByEmail(String email) {
		return hotelUserRepository.findByEmail(email);
	}

	@Override
	public void deleteInBatch(List<HotelUser> users) {
		hotelUserRepository.deleteInBatch(users);
	}
	
}
