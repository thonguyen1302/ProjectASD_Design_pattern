package mum.asd.service.impl;

import mum.asd.domain.*;
import mum.asd.repository.*;
import org.omg.PortableInterceptor.HOLDING;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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
        addSampleBooking();
        addSamplePayment();
        addSampleCard();
        addSamplePromotion();
        addSampleUser();
        addSampleReceipt();
        addSampleRoom();
        addAddressToHotelUser();
        addPaymentToHotelUser();
        addCardToPayment();


    }

    public void addPaymentToHotelUser(){
        List<HotelUser> hotelUsers = hotelUserRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();
        for (int i = 0; i<hotelUsers.size();i++){
            HotelUser hotelUser = hotelUsers.get(i);
            hotelUser.setPayment(payments.get(i));
            hotelUserRepository.save(hotelUser);
        }
    }

    public void addAddressToHotelUser(){
        List<Address> addresses = addressRepository.findAll();
        List<HotelUser> hotelUsers = hotelUserRepository.findAll();
        int n = addresses.size()>hotelUsers.size()?hotelUsers.size():addresses.size();

        for (int i = 0; i<n;i++){
            HotelUser temp = hotelUsers.get(i);
            temp.setAddress(addresses.get(i));
            hotelUserRepository.save(temp);
        }
    }


    public void addSampleReceipt(){
        if (receiptRepository.findAll().size()==0){
            addReceipt(1111,1112,600);
            addReceipt(2111,2112,650);
            addReceipt(3111,3112,800);
        }
    }

    public void addReceipt(int bookingNumber, int receiptNumber, float total){
        Receipt receipt = new Receipt();
        receipt.setBookingNumber(bookingNumber);
        receipt.setReceiptNumber(receiptNumber);
        receipt.setTotal(total);
    }

    public void addSamplePayment(){
        if (paymentRepository.findAll().size()==0){
            addPayment(500,"Credit");
            addPayment(750,"Credit");
            addPayment(1000,"Debit");
            addPayment(1500,"Debit");
        }

    }

    public void addPayment(float amount, String type){
        Payment payment1 = new Payment();
        payment1.setAmount(amount);
        payment1.setType(type);
        paymentRepository.save(payment1);
    }

    public void addSampleCard(){
        if (cardRepository.findAll().size()==0){
            addCard("900876574434", new Date(),"Nguyen Van Anh", "832","07-2020" );
            addCard("900876574435", new Date(),"Nguyen Tuan Dung", "116","05-2020" );
            addCard("900876574436", new Date(),"Nguyen Jone", "332","07-2021" );
            addCard("900876574437", new Date(),"Tran Tien", "567","12-2020" );
        }
    }

    public void addCard(String cardNumber, Date expiredDate, String holderName, String pinNumber, String expiredDateString){
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setExpiredDate(expiredDate);
        card.setHoldername(holderName);
        card.setPinNumber(pinNumber);
        card.setExpiredDateS(expiredDateString);
        cardRepository.save(card);
    }

    public void addCardToPayment(){
        List<Payment> payments = paymentRepository.findAll();
        if (payments.get(0).getCards().size()==0){
            List<Card> cards = cardRepository.findAll();
            for (int i = 0; i<payments.size(); i++){
                Payment payment = payments.get(i);
                payment.getCards().add(cards.get(i));
                paymentRepository.save(payment);
            }
        }
    }


    public void addSampleUser(){
        if (hotelUserRepository.findAll().size()==0){
            addUser("639890776576","sang@gmail.com","Sang","Tran",Gender.Male,"123","90087654563",UserType.Admin);
            addUser("63989077653","tho@gmail.com","Tho","Nguyen",Gender.Male,"123","90087654444",UserType.Admin);
            addUser("639890776236","vy@gmail.com","Vy","Nguyen",Gender.Female,"123","90087654223",UserType.Admin);
            addUser("639222276236","user@gmail.com","Jone","Doe",Gender.Male,"123","90011114223",UserType.Customer);
        }
    }

    public void addUser(String credit, String email, String firstName, String lastName, Gender gender, String password, String phone, UserType userType){
        HotelUser hotelUser = new HotelUser();
        hotelUser.setEmail(email);
        hotelUser.setPassword(password);
        hotelUser.setFirstName(firstName);
        hotelUser.setLastName(lastName);
        hotelUser.setPhone(phone);
        hotelUser.setGender(gender);
        hotelUser.setUserType(userType);
        hotelUserRepository.save(hotelUser);
    }


    public void addSampleRoom(){
        if (roomRepository.findAll().size() == 0){
            addRoom(101,"Grand", 7.6f, 300, 2, 2, true,RoomType.Deluxe);
            addRoom(102,"GrandQueen", 7f, 350, 2, 2, true,RoomType.Standard);
            addRoom(103,"GrandWest", 7.1f, 310, 2, 2, true,RoomType.Suite);
            addRoom(104,"GrandBeach", 7.2f, 320, 2, 2, true,RoomType.Deluxe);
            addRoom(105,"GrandHill", 7.3f, 280, 2, 2, true,RoomType.Suite);
            addRoom(201,"Grand", 7.6f, 300, 2, 2, true,RoomType.Deluxe);
            addRoom(202,"GrandQueen", 7f, 350, 2, 2, true,RoomType.Standard);
            addRoom(203,"GrandWest", 7.1f, 310, 2, 2, true,RoomType.Suite);
            addRoom(204,"GrandBeach", 7.2f, 320, 2, 2, true,RoomType.Deluxe);
            addRoom(205,"GrandHill", 7.3f, 280, 2, 2, true,RoomType.Suite);
            addRoom(301,"Grand", 7.6f, 300, 2, 2, true,RoomType.Deluxe);
            addRoom(302,"GrandQueen", 7f, 350, 2, 2, true,RoomType.Standard);
            addRoom(303,"GrandWest", 7.1f, 310, 2, 2, true,RoomType.Suite);
            addRoom(304,"GrandBeach", 7.2f, 320, 2, 2, true,RoomType.Deluxe);
            addRoom(305,"GrandHill", 7.3f, 280, 2, 2, true,RoomType.Suite);
        }
    }

    public void addRoom(int roomNumber, String bedType, float tax, int price, int numChildren, int numAdult, boolean isVailable,RoomType roomType){
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setTax(tax);
        room.setPrice(price);
        room.setNumberChildren(numChildren);
        room.setNumberAdult(numAdult);
        room.setBedType(bedType);
        room.setRoomVailable(isVailable);
        room.setRoomType(roomType);
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
             address = new Address();
            address.setCity("Fairfield");
            address.setState("IOWA");
            address.setStreet("5th Bủlington");
            address.setZipcode("52556");
            addressRepository.save(address);
             address = new Address();
            address.setCity("Fairfield");
            address.setState("IOWA");
            address.setStreet("12th Jeferson");
            address.setZipcode("52556");
            addressRepository.save(address);
        }
    }

}
