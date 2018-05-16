package mum.asd.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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

@Controller
public class BookingController implements Initializable {
	
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
	private TableView<Room> userTable;
	
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

}
