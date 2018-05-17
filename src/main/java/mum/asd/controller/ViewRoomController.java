package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mum.asd.Main;
import mum.asd.config.StageManager;
import mum.asd.domain.Address;
import mum.asd.domain.BedType;
import mum.asd.domain.Card;
import mum.asd.domain.HotelUser;
import mum.asd.domain.Payment;
import mum.asd.domain.Room;
import mum.asd.domain.RoomType;
import mum.asd.domain.User;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;
import mum.asd.view.FxmlView;

@Controller
public class ViewRoomController implements Initializable {
	
	@FXML
	private Label userId;
	
	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@FXML
    private void pay(ActionEvent event) {
		
		// Init data for testing
		HotelUser user = new HotelUser();
		user.setFirstName("Vy");
		user.setLastName("Nguyen");
		user.setAddress(new Address("1000 N 4th St", "FF", "Iowa", "52557"));
		
		Payment payment = new Payment();
		Card card1 = new Card();
		card1.setCardNumber("123456789");
		card1.setPinNumber("112");
		card1.setHoldername("Le Cat Vy Nguyen");
		
		Card card2 = new Card();
		card2.setCardNumber("987654321");
		card2.setPinNumber("333");
		card2.setHoldername("Vy Nguyen");
		
		String sDate1="31/12/1998";
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		card1.setExpiredDate(date1);
		card2.setExpiredDate(date1);
		
		payment.addCard(card1);
		payment.addCard(card2);
		user.setPayment(payment);
		
		// List rooms is booked
		Room r1 = new Room(BedType.Double.toString(), 2, 2, 7, true, 001, 130, RoomType.Deluxe);
		Room r2 = new Room(BedType.Twin.toString(), 2, 2, 8, true, 002, 120, RoomType.Standard);
		Room r3 = new Room(BedType.Single.toString(), 2, 2, 5, true, 003, 140, RoomType.Suite);
		Room r4 = new Room(BedType.Double.toString(), 2, 2, 7, true, 004, 110, RoomType.Deluxe);
		List<Room> lstRooms = new ArrayList<Room>();
		lstRooms.add(r1); lstRooms.add(r2); lstRooms.add(r3); lstRooms.add(r4);
		
		ServiceBuilder serviceBuilder = new ConcreteServiceBuilder(user);
		ServiceDirector serviceDirector = new ServiceDirector(serviceBuilder);
		serviceDirector.createBooking(date1, date1);
		serviceDirector.setRoomsToBooking(lstRooms);
		   
		try {
			// call booking form
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Booking.fxml"));
			
			Parent root = (Parent)fxmlLoader.load();
			
			BookingController controller = fxmlLoader.<BookingController>getController();
			controller.setServiceDirector(serviceDirector);
			Scene scene = new Scene(root); 
			Stage stage = Main.getPrimaryStage();
			stage.setScene(scene);    

			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//stageManager.switchScene(FxmlView.BOOKING);   
	}
	
	@FXML
    private void logout(ActionEvent event) {
		
	}
	
	@FXML
    private void deleteRoom(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}