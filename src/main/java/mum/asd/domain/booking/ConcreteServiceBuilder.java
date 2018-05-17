package mum.asd.domain.booking;

import java.util.Date;
import java.util.List;

import mum.asd.domain.Booking;
import mum.asd.domain.HotelUser;
import mum.asd.domain.Room;
import mum.asd.domain.User;
import mum.asd.service.BookingService;
import mum.asd.service.HotelUserService;
import mum.asd.service.UserService;
import mum.asd.service.impl.BookingServiceImpl;
import mum.asd.service.impl.HotelUserServiceImpl;
import mum.asd.service.impl.UserServiceImpl;

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
		BookingService bookingService = new BookingServiceImpl();
		bookingService.save(this.booking);
		
		// Save user to have relationship
		String email = this.user.getEmail();
		HotelUserService userService = new HotelUserServiceImpl();
		HotelUser currentUser = userService.findByEmail(email);
		currentUser.addBookingToBookingList(this.booking);
		userService.save(currentUser);
		
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
