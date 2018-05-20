package mum.asd.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mum.asd.domain.*;
import mum.asd.patterns.FactoryMethod.ConcretePromotionFactory;
import mum.asd.patterns.FactoryMethod.HolidayPromotion;
import mum.asd.patterns.FactoryMethod.PromotionFactory;
import mum.asd.repository.HotelUserRepository;
import mum.asd.repository.UserRepository;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.PaymentService;
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
public class ViewRoomController extends ApplicationController implements Initializable, ChangeListener {

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
	public TextField searchBox;
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
	private String searchKeyword;
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");



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

	public void loadRoomTable(boolean withKeyword){
		List<Room> rooms=null;
		if (roomService == null){
			roomService = ApplicationContextHolder.getContext().getBean(RoomServiceImpl.class);
		}
		if (withKeyword){
			rooms = roomService.findAvailableRoomByKeyword(this.searchKeyword);
		}else {
			rooms = roomService.findAvailableRoom();
		}
		roomListObservable.clear();
		roomListObservable.addAll(rooms);
		roomTableView.setItems(roomListObservable);

		loadStartDateEndDate();
		loadPromotion();
	}

	public void loadPromotion(){
		HotelUserRepository userRepository = ApplicationContextHolder.getContext().getBean(HotelUserRepository.class);

		HotelUser hotelUser = userRepository.findOne(currentUser.getId());
		List<Promotion> promotions = hotelUser.getPromotions();
		int discountTotal = 0;
		for (Promotion promotion: promotions){
			PromotionFactory promotionFactory = new ConcretePromotionFactory();

			discountTotal += promotionFactory.createPromotion(promotion.getName()).executeGetDiscout();
		}
		discount.setText(discountTotal+"");
	}

	public void loadStartDateEndDate(){
		Date dt = getDaysFromNow(1);
		startDate.setText(dateFormat.format(getDaysFromNow(1)));
		endDate.setText(dateFormat.format(getDaysFromNow(2)));
//		System.out.println(dateFormat.format(dt));
	}

	public Date getDaysFromNow(int i){
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, i);
		dt = c.getTime();
		return dt;
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

		loadRoomTable(false);
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
        searchBox.textProperty().addListener(this);

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

	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {
		System.out.println(newValue);
		this.searchKeyword = newValue.toString();
		if (newValue.toString().equals("")){
			loadRoomTable(false);
		}else {
			loadRoomTable(true);
		}
	}
}
