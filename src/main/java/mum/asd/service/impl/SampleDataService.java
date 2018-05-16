package mum.asd.service.impl;

import mum.asd.domain.Address;
import mum.asd.domain.Booking;
import mum.asd.domain.Promotion;
import mum.asd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SampleDataService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    HotelUserRepository hotelUserRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    public void generateSampleDate(){
        
    }

    public void addSampleBooking(){
        addBooking("123", true, false, new Date(), new Date());
        addBooking("124", true, false, new Date(), new Date());
        addBooking("125", true, false, new Date(), new Date());
        addBooking("126", true, false, new Date(), new Date());
    }

    public void addBooking(String bookingNumber, boolean checkInStatus, boolean checkOutStatus, Date endDate, Date startDate){
        Booking booking = new Booking();
        booking.setBookingNumber(bookingNumber);
        booking.setCheckInStatus(checkInStatus);
        booking.setCheckOutStatus(checkOutStatus);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        bookingRepository.save(booking);
    }

    public void addSamplePromotion(){
        addPromotion("SpringHoliday", 15, 20);
        addPromotion("FallHoliday", 10, 25);
        addPromotion("SummerHoliday", 25, 10);
        addPromotion("WinterHoliday", 5, 5);

    }

    public boolean addPromotion(String name, int discount, float percent){
        Promotion promotion = new Promotion();
        promotion.setName(name);
        promotion.setDiscount(discount);
        promotion.setPercent(percent);
        if (promotionRepository.save(promotion)!=null){
            return true;
        }
        return false;
    }

    public void addSampleAddress(){
        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IOWA");
        address.setStreet("4th Street");
        address.setZipcode("52557");
        addressRepository.save(address);
        address = new Address();
        address.setCity("Fairfield");
        address.setState("IOWA");
        address.setStreet("1302 S Main Street");
        address.setZipcode("52556");
        addressRepository.save(address);

    }

}
