package mum.asd.service.impl;

import mum.asd.domain.Address;
import mum.asd.domain.Booking;
import mum.asd.domain.Promotion;
import mum.asd.domain.Room;
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

    public void generateSampleData(){
        addSampleAddress();
        addSamplePromotion();
        addSampleBooking();
        addSampleRoom();
    }

    public void addSampleRoom(){
        if (roomRepository.findAll().size() == 0){
            addRoom(101,"Grand", 7.6f, 300, 2, 2, true);
            addRoom(102,"GrandQueen", 7f, 350, 2, 2, true);
            addRoom(103,"GrandWest", 7.1f, 310, 2, 2, true);
            addRoom(201,"GrandBeach", 7.2f, 320, 2, 2, true);
            addRoom(202,"GrandHill", 7.3f, 280, 2, 2, true);
        }
    }

    public void addRoom(int roomNumber, String bedType, float tax, int price, int numChildren, int numAdult, boolean isVailable){
        Room room = new Room();
        room.setTax(tax);
        room.setPrice(price);
        room.setNumberChildren(numChildren);
        room.setNumberAdult(numAdult);
        room.setBedType(bedType);
        room.setRoomVailable(isVailable);
        roomRepository.save(room);
    }

    public void addSampleBooking(){
        if (bookingRepository.findAll().size()==0){
            addBooking("123", true, false, new Date(), new Date());
            addBooking("124", true, false, new Date(), new Date());
            addBooking("125", true, false, new Date(), new Date());
            addBooking("126", true, false, new Date(), new Date());
        }
    }

    public void addBooking(String bookingNumber, boolean checkInStatus, boolean checkOutStatus, Date endDate, Date startDate){
        Booking booking = new Booking(startDate, endDate);
        booking.setBookingNumber(bookingNumber);
        booking.setCheckInStatus(checkInStatus);
        booking.setCheckOutStatus(checkOutStatus);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        bookingRepository.save(booking);
    }

    public void addSamplePromotion(){
        if (promotionRepository.findAll().size()==0){
            addPromotion("SpringHoliday", 15, 20);
            addPromotion("FallHoliday", 10, 25);
            addPromotion("SummerHoliday", 25, 10);
            addPromotion("WinterHoliday", 5, 5);
        }
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
        if (addressRepository.findAll().size() == 0){
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

}
