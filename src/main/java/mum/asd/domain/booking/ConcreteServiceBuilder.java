package mum.asd.domain.booking;

import java.util.Date;
import java.util.List;

import mum.asd.domain.Booking;
import mum.asd.domain.HotelUser;
import mum.asd.domain.Room;
import mum.asd.domain.User;

/**
 * @author vynguyen
 *
 */
public class ConcreteServiceBuilder implements ServiceBuilder {
	private HotelUser user;
	private Booking booking;
	
	public ConcreteServiceBuilder(HotelUser user) {
		super();
		this.user = user;
	}

	@Override
	public void createBooking(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		this.booking = new Booking(startDate, endDate);
	}

	@Override
	public void setRoomsToBooking(List<Room> lstRoom) {
		// TODO Auto-generated method stub
		this.booking.setRooms(lstRoom);
	}

	@Override
	public void saveBooking() {
		// TODO Auto-generated method stub
		// Call api to save to booking table
		// Read booking, get booking id
		
		// Save to booking_rooms table
	}

	public HotelUser getUser() {
		return user;
	}

	public Booking getBooking() {
		return booking;
	}
}
