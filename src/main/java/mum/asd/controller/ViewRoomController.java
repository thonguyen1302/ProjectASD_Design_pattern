package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mum.asd.domain.*;
import mum.asd.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mum.asd.Main;
import mum.asd.config.StageManager;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;

@Controller
public class ViewRoomController implements Initializable {

	public TableColumn<Room, String> colAdults;
	public TableColumn<Room, String> colChildren;
	public TableColumn<Room, String> colRoomType;
	public TableColumn<Room, String> colStatus;
	public TableColumn<Room, String> colTax;
    public TextField startDate;
	public TextField endDate;
	public TextField totalRoomsSelected;
	public TextField discount;
	public Button pay;
	@Autowired
	RoomServiceImpl roomService;

	public List<Room> selectedRooms;

	@FXML
	public TableView<Room> roomTableView;
	@FXML
	public TableColumn<Room, String> colRoomNumber;
	@FXML
	public TableColumn<Room, String> colPrice;
	@FXML
	public TableColumn<Room, String> colBedType;
	@FXML
	private Label userId;


	private ObservableList<Room> roomListObservable = FXCollections.observableArrayList();
	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@FXML
    private void pay(ActionEvent event) {

		System.out.println("Pay button pressed");
		
		// Init data for testing
//		HotelUser user = new HotelUser();
//		user.setFirstName("Vy");
//		user.setLastName("Nguyen");
//		user.setEmail("vynguyenlc@gmail.com");
//		user.setAddress(new Address("1000 N 4th St", "FF", "Iowa", "52557"));
//
//		Payment payment = new Payment();
//		payment.setId(1);
//		Card card1 = new Card();
//		card1.setCardNumber("123456789");
//		card1.setPinNumber("112");
//		card1.setHoldername("Le Cat Vy Nguyen");
//
//		Card card2 = new Card();
//		card2.setCardNumber("987654321");
//		card2.setPinNumber("333");
//		card2.setHoldername("Vy Nguyen");
//
//		String sDate1="31/12/1998";
//		Date date1 = null;
//		try {
//			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		card1.setExpiredDate(date1);
//		card2.setExpiredDate(date1);
//
//		payment.addCard(card1);
//		payment.addCard(card2);
//		user.setPayment(payment);
//
//		// List rooms is booked
//		Room r1 = new Room(BedType.Double.toString(), 2, 2, 7, true, 001, 130, RoomType.Deluxe);
//		r1.setId(1);
//		Room r2 = new Room(BedType.Twin.toString(), 2, 2, 8, true, 002, 120, RoomType.Standard);
//		r2.setId(2);
//		Room r3 = new Room(BedType.Single.toString(), 2, 2, 5, true, 003, 140, RoomType.Suite);
//		r3.setId(3);
//		Room r4 = new Room(BedType.Double.toString(), 2, 2, 7, true, 004, 110, RoomType.Deluxe);
//		r4.setId(4);
//		List<Room> lstRooms = new ArrayList<Room>();
//		lstRooms.add(r1); lstRooms.add(r2); lstRooms.add(r3); lstRooms.add(r4);
//
//		ServiceBuilder serviceBuilder = new ConcreteServiceBuilder(user);
//		ServiceDirector serviceDirector = new ServiceDirector(serviceBuilder);
//		serviceDirector.createBooking(date1, date1);
//		serviceDirector.setRoomsToBooking(lstRooms);
//
//		try {
//			// call booking form
//			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Booking.fxml"));
//
//			Parent root = (Parent)fxmlLoader.load();
//
//			BookingController controller = fxmlLoader.<BookingController>getController();
//			controller.setServiceDirector(serviceDirector);
//			Scene scene = new Scene(root);
//			Stage stage = Main.getPrimaryStage();
//			stage.setScene(scene);
//
//			stage.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//stageManager.switchScene(FxmlView.BOOKING);
	}
	
	@FXML
    private void logout(ActionEvent event) {
		
	}
	
	@FXML
    private void deleteRoom(ActionEvent event) {
		
	}

	public void loadRoomTable(){
		List<Room> rooms = roomService.findAll();
		roomListObservable.clear();
		roomListObservable.addAll(rooms);
		roomTableView.setItems(roomListObservable);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        roomTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        addSelectionListener();

		loadRoomTable();
	}

    public List<Room> getSelectedRooms() {
        return selectedRooms;
    }

    public void setSelectedRooms(List<Room> selectedRooms) {
        this.selectedRooms = selectedRooms;
    }

    public void addSelectionListener(){
        roomTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedRooms = roomTableView.getSelectionModel().getSelectedItems();
//            System.out.println();
                });
    }


    private void setColumnProperties() {
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
		colAdults.setCellValueFactory(new PropertyValueFactory<>("numberAdult"));
		colChildren.setCellValueFactory(new PropertyValueFactory<>("numberChildren"));
		colTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colStatus.setCellValueFactory(cellData -> {
            boolean roomVailable = cellData.getValue().isRoomVailable();
            String availableAsString;
            if(roomVailable == true)
            {
                availableAsString = "Available";
            }
            else
            {
                availableAsString = "Occupated";
            }

            return new ReadOnlyStringWrapper(availableAsString);
        });

	}

}
