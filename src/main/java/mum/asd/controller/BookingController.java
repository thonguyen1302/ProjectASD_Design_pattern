package mum.asd.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mum.asd.domain.Card;
import mum.asd.domain.Room;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;
import mum.asd.service.BookingService;

/**
 * @author vynguyen
 *
 */
@Controller
public class BookingController implements Initializable {
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
	
	@Autowired
	private BookingService bookingService;
	
	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@FXML
    private void cancel(ActionEvent event) {
		
	}
	
	@FXML
    private void pay(ActionEvent event) {
		this.serviceDirector.getServiceBuilder().saveBooking();
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

	// Use for passing data from view book controller to booking controller
	public void setServiceDirector(ServiceDirector serviceDirector) {
		this.serviceDirector = serviceDirector;
		ConcreteServiceBuilder concreteServiceBuilder =
						(ConcreteServiceBuilder)this.serviceDirector.getServiceBuilder();
		
		// Init user information to GUI
		this.name.setText(concreteServiceBuilder.getUser().getFirstName() + 
				" " + concreteServiceBuilder.getUser().getLastName());
		this.address.setText(concreteServiceBuilder.getUser().getAddress().toString());
		
		List<String> numCard = new ArrayList<>();
		for (Card c : concreteServiceBuilder.getUser().getPayment().getCards()) {
			String cardNumber = c.getCardNumber();
			numCard.add("xxxxxx" + cardNumber.substring(0, cardNumber.length() - 5));
		} // need to process card and payment
		
		this.cardNumber.getItems().addAll(numCard);
		this.startDate.setText(concreteServiceBuilder.getBooking().getStartDate().toString());
		this.endDate.setText(concreteServiceBuilder.getBooking().getEndDate().toString());
		
		// Count total price
		List<Room> lstRoom = concreteServiceBuilder.getBooking().getRooms();
		ObservableList<Room> data = FXCollections.observableArrayList(lstRoom);
		this.roomsTable = new TableView<Room>(data);
		this.colRoomNumber = new TableColumn<>("Room No");
		colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		roomsTable.getColumns().add(colRoomNumber);
		/*roomsTable.getColumns().add(colPrice);
		roomsTable.getColumns().add(colBedType);
		roomsTable.getColumns().add(colAdults);
		roomsTable.getColumns().add(colChildren);
		roomsTable.getColumns().add(colRoomType);
		roomsTable.getColumns().add(colStatus);*/
		
		this.roomsTable.setItems(data);
		//for (Room r : lstRoom) {
//			colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
//			colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//			colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
//			colAdults.setCellValueFactory(new PropertyValueFactory<>("numberAdult"));
//			colChildren.setCellValueFactory(new PropertyValueFactory<>("numberChildren"));
//			colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
//			colStatus.setCellValueFactory(new PropertyValueFactory<>("isRoomVailable"));
//			colTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		//}
		
	}
	
	public ServiceDirector getServiceDirector() {
		return this.serviceDirector;
	}
}
