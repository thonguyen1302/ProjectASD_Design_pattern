package mum.asd.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mum.asd.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
import mum.asd.domain.Room;
import mum.asd.domain.User;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;

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
	private TableView<Room> roomTableView;
	
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
	RoomService roomService;
	
	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@FXML
    private void cancel(ActionEvent event) {
		
	}
	
	@FXML
    private void pay(ActionEvent event) {
		
		
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
	}
	
	// Use for saving booking later
	public void setListRoomsBooked(Date startDate, Date endDate) {
		this.serviceDirector.createBooking(startDate, endDate);
	}
		
	// Use for saving booking later
	public void setListRoomsBooked(List<Room> lstRoom) {
		this.serviceDirector.setRoomsToBooking(lstRoom);
	}
	
	public ServiceDirector getServiceDirector() {
		return this.serviceDirector;
	}
}
