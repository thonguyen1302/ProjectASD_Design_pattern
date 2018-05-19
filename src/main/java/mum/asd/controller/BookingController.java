package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import mum.asd.domain.HotelUser;
import mum.asd.domain.Payment;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.CardService;
import mum.asd.service.PaymentService;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mum.asd.Main;
import mum.asd.domain.Card;
import mum.asd.domain.Room;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;
import mum.asd.domain.bookingprices.ServiceElementDoVisitor;

import javax.transaction.Transactional;

/**
 * @author vynguyen
 *
 */
@Controller
@Transactional
public class BookingController extends ApplicationController implements Initializable {
	private ServiceDirector serviceDirector;
	
	@FXML
	private TextField name;
	
	@FXML
	private Label userId;
	
	@FXML
	private TextField address;
	
	@FXML
	private ComboBox<String> cardNumber;
	
	@FXML
	private Button addNewCard;
	
	@FXML
	private TextField startDate;
	
	@FXML
	private TextField endDate;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button pay;
	
	@FXML
	private Button btnLogout;
	
	@FXML
	private TableView<Room> roomsTable;
	
	@FXML
	private TextField totalPrice;
	
	@FXML
	private TextField discount;
	
	@FXML
	private TableColumn<Room, Long> colRoomNumber;

	@FXML
	private TableColumn<Room, String> colPrice;

	@FXML
	private TableColumn<Room, LocalDate> colBedType;

	@FXML
	private TableColumn<Room, String> colAdults;
	
	@FXML
    private TableColumn<Room, String> colChildren;

	@FXML
	private TableColumn<Room, String> colRoomType;
	
	@FXML
    private TableColumn<Room, Boolean> colStatus;
	
	@FXML
	private TableColumn<Room, String> colTax;
	
	@FXML
    private MenuItem deleteRoom;

	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@FXML
    private void cancel(ActionEvent event) {
		goToViewRoomLayout();
	}
	
	@FXML
    private void pay(ActionEvent event) {
		if (this.cardNumber.getValue() != null) {
			this.serviceDirector.getServiceBuilder().saveBooking();
			showAlert(ResourceBundle.getBundle("Bundle").getString("booking.complete"), 
					AlertType.INFORMATION);
			goToViewRoomLayout();
		} else {
			showAlert(ResourceBundle.getBundle("Bundle").getString("booking.warning"), 
							AlertType.WARNING);
		}
	}
	
	@FXML
    private void logout(ActionEvent event) {
		
	}
	
	@FXML
    private void deleteRoom(ActionEvent event) {
		
	}
	
	@FXML
    private void addNewCard(ActionEvent event) {
		gotoAddCardLayout(this.serviceDirector);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	// Use for passing data from view book controller to booking controller
	public void setServiceDirector(ServiceDirector serviceDirector) {
		
		// Using builder pattern to do booking action
		this.serviceDirector = serviceDirector;
		ConcreteServiceBuilder concreteServiceBuilder =
						(ConcreteServiceBuilder)this.serviceDirector.getServiceBuilder();
		
		// Show user information to GUI
		this.name.setText(concreteServiceBuilder.getUser().getFirstName() + 
				" " + concreteServiceBuilder.getUser().getLastName());
		this.address.setText(concreteServiceBuilder.getUser().getAddress().toString());
		
		// Show list cards of user if have
		List<String> numCard = new ArrayList<>();
		HotelUser hotelUser = concreteServiceBuilder.getUser();
		Payment payment = hotelUser.getPayment();
		PaymentService paymentService = ApplicationContextHolder.getContext().getBean(PaymentService.class);
		List<Card> cards = paymentService.getListCardsByPayment(payment);
		for (Card c : cards) {
			String cardNumber = c.getCardNumber();
			numCard.add("xxxxxx" + cardNumber.substring(0, cardNumber.length() - 5));
		}
		
		this.cardNumber.getItems().addAll(numCard);
		this.startDate.setText(concreteServiceBuilder.getBooking().getStartDate_S());
		this.endDate.setText(concreteServiceBuilder.getBooking().getEndDate_S());
		
		List<Room> lstRoom = concreteServiceBuilder.getBooking().getRooms();
		Double discountPercent = 0.0;
		// Verify discount
		if (lstRoom != null){
			if (lstRoom.size() > 2 && lstRoom.size() < 5) {
				discountPercent = 0.1;
			} else if (lstRoom.size() > 5) {
				discountPercent = 0.3;
			}
			this.discount.setText(String.valueOf(discountPercent));


		// Count total price using visitor pattern
		ServiceElementDoVisitor serviceElementVisitor = new ServiceElementDoVisitor();
		for (Room r : lstRoom) {
			r.accept(serviceElementVisitor);
		}
		double finalPrice = serviceElementVisitor.getPrice();
		if (discountPercent > 0) {
			finalPrice = finalPrice - (finalPrice * discountPercent);
		}
		
		this.totalPrice.setText(String.valueOf(finalPrice));
		
		
		// Load list room to table view
		//List<Room> lstRoom = concreteServiceBuilder.getBooking().getRooms();
		ObservableList<Room> data = FXCollections.observableArrayList(lstRoom);
		//this.roomsTable = new TableView<Room>(data);
		//this.colRoomNumber = new TableColumn<>("Room No");
		//colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		//roomsTable.getColumns().add(colRoomNumber);
		/*roomsTable.getColumns().add(colPrice);
		roomsTable.getColumns().add(colBedType);
		roomsTable.getColumns().add(colAdults);
		roomsTable.getColumns().add(colChildren);
		roomsTable.getColumns().add(colRoomType);
		roomsTable.getColumns().add(colStatus);*/
		
		//this.roomsTable.setItems(data);
		//for (Room r : lstRoom) {
			colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
			colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
			colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
			colAdults.setCellValueFactory(new PropertyValueFactory<>("numberAdult"));
			colChildren.setCellValueFactory(new PropertyValueFactory<>("numberChildren"));
			colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
			colStatus.setCellValueFactory(new PropertyValueFactory<>("isRoomVailable"));
			colTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		//}
			
			data.clear();
			data.addAll(lstRoom);
			roomsTable.setItems(data);
		}
	}
	
	public ServiceDirector getServiceDirector() {
		return this.serviceDirector;
	}
}
