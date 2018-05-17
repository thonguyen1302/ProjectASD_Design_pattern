package mum.asd.domain.booking;

import java.util.Date;
import java.util.List;

import mum.asd.domain.Booking;
import mum.asd.domain.HotelUser;
import mum.asd.domain.Room;
import mum.asd.domain.User;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.BookingService;
import mum.asd.service.HotelUserService;
import mum.asd.service.UserService;
import mum.asd.service.impl.BookingServiceImpl;
import mum.asd.service.impl.HotelUserServiceImpl;
import mum.asd.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author vynguyen
 *
 */
//@Component
//@Configurable
public class ConcreteServiceBuilder implements ServiceBuilder {
//	@Autowired
	private HotelUser user;
	private Booking booking;

	@Autowired
	BookingService bookingService;

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
		// Call api to save to booking table
		bookingService = ApplicationContextHolder.getContext().getBean(BookingService.class);
		bookingService.save(this.booking);
		
		// Save user to have relationship
		String email = this.user.getEmail();
		HotelUserService userService = ApplicationContextHolder.getContext().getBean(HotelUserService.class);
		HotelUser currentUser = userService.findByEmail(email);
		currentUser.addBookingToBookingList(this.booking);
		userService.save(currentUser);
	}

	public HotelUser getUser() {
		return user;
	}

	public Booking getBooking() {
		return booking;
	}
}
