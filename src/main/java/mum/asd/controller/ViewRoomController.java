package mum.asd.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
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
import javafx.fxml.Initializable;
import mum.asd.config.StageManager;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;

@Controller
public class ViewRoomController extends ApplicationController implements Initializable {

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
	@FXML
	private Button btnLogout;
	@FXML
	private Label userMess;

	private ObservableList<Room> roomListObservable = FXCollections.observableArrayList();
	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@Lazy
    @Autowired
    private StageManager stageManager;

	public TextField getStartDate() {
		return startDate;
	}

	public void setStartDate(TextField startDate) {
		this.startDate = startDate;
	}

	@FXML
    private void pay(ActionEvent event) {

		System.out.println("Pay button pressed");
		List<Room> listSelectedRooms = getSelectedRooms();

		String endDate = this.endDate.getText();
		String startDate = this.startDate.getText();
		if (listSelectedRooms!=null&&listSelectedRooms.size()>0){
			int totalRoomSelected = Integer.parseInt(this.totalRoomsSelected.getText());
			this.totalRoomsSelected.setText(totalRoomSelected+"");
		}
		if (currentUser!=null){
			List<Promotion> promotions = currentUser.getPromotions();
			if (promotions != null && promotions.size()>0){
				this.discount.setText(promotions.get(0).getName());
			}
		}
		System.out.println();

		// Prepare data to send to booking layout
		ServiceBuilder serviceBuilder = new ConcreteServiceBuilder(currentUser);
		ServiceDirector serviceDirector = new ServiceDirector(serviceBuilder);
		serviceDirector.createBooking(startDate, endDate);
		serviceDirector.setRoomsToBooking(listSelectedRooms);
		
		// Go to booking layout
		goToBookingLayout(serviceDirector);
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
		
		// Update message to say hello to user
		if (currentUser != null) {
			userMess.setText("Hello " + currentUser.getFirstName() + " !");
		}
		
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
			this.totalRoomsSelected.setText(selectedRooms.size()+"");
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
