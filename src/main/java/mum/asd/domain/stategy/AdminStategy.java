package mum.asd.domain.stategy;

import mum.asd.domain.HotelUser;
import mum.asd.domain.UserType;

public class AdminStategy implements UserStategy {

	@Override
	public HotelUser setUserType(HotelUser user) {
		// TODO Auto-generated method stub
		user.setUserType(UserType.Admin);
		return user;
	}

}
