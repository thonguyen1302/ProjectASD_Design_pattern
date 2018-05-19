package mum.asd.domain.stategy;

import mum.asd.domain.HotelUser;

public class UserType {
	private UserStategy userStategy;
	
	public void setStategy(UserStategy userStategy) {
		this.userStategy = userStategy;
	}
	
	public HotelUser createUser(HotelUser user) {
		return userStategy.setUserType(user);
	}
}
