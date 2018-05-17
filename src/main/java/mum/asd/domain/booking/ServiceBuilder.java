package mum.asd.domain.booking;

import java.util.Date;
import java.util.List;

import mum.asd.domain.Room;

/**
 * @author vynguyen
 *
 */
public interface ServiceBuilder {
	public void createBooking(Date startDate, Date endDate);
	public void setRoomsToBooking(List<Room> lstRoom);
	public void saveBooking();
}
