package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mum.asd.domain.Room;
import mum.asd.service.impl.RoomService;
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
import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;
import mum.asd.view.FxmlView;

@Controller
public class ViewRoomController implements Initializable {
	
	@FXML
	private Label userId;

	@Autowired
	RoomService roomService;

	@FXML
    private void exit(ActionEvent event) {
		
	}


	private ObservableList<Room> roomsList = FXCollections.observableArrayList();

	@Lazy
    @Autowired
    private StageManager stageManager;


	private void loadRoomDetails(){
		roomsList.clear();
		roomsList.addAll(roomService.findAll());

		roomTableView.setItems(roomsList);
	}

	@FXML
    private void pay(ActionEvent event) {
		HotelUser user = new HotelUser();
		user.setFirstName("Vy");
		user.setLastName("Nguyen");
		//user.setAddress(new Address());
		
		ServiceBuilder serviceBuilder = new ConcreteServiceBuilder(user);
		ServiceDirector serviceDirector = new ServiceDirector(serviceBuilder);
		   
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
