package mum.asd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mum.asd.domain.Address;
import mum.asd.domain.HotelUser;
import mum.asd.service.HotelUserService;
import mum.asd.view.FxmlView;

@Controller
public class UserInfoController extends ApplicationController implements Initializable{
			
	@FXML
	private TextField email;
	
	@FXML
    private TextField firstName;

	@FXML
    private TextField lastName;
	
	@FXML
    private TextField phone;
	
	@FXML
    private TextField gender;
	
	@FXML
    private PasswordField password;
	
	@FXML
    private PasswordField confirmPassword;
	
	@Autowired
	private HotelUserService hotelUserService;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.email.setText(currentUser.getEmail());
		
		this.firstName.setText(currentUser.getFirstName());;

		this.lastName.setText(currentUser.getLastName());;
		
		this.phone.setText(currentUser.getPhone());;
		
		this.password.setText(currentUser.getPassword());;
		
		this.confirmPassword.setText(currentUser.getPassword());;
	}
	
	@FXML
    private void btnSave(ActionEvent event){

    	if(validate("Email", getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
    	   validate("First Name", getFirstName(), "[a-zA-Z]+") &&
    	   validate("Last Name", getLastName(), "[a-zA-Z]+") && 
    	   emptyValidation("Password", getPassword().isEmpty()) &&
    	   emptyValidation("Confirm Password", getConfirmPassword().isEmpty()) &&
    	   validatePassword(getPassword(), getConfirmPassword())
    	   ){
    
			HotelUser hotelUser = new HotelUser();
			hotelUser.setEmail(getEmail());
			hotelUser.setFirstName(getFirstName());
			hotelUser.setLastName(getLastName());
			hotelUser.setPhone(getPhone());
			hotelUser.setPassword(getPassword());
			
			
			hotelUserService.save(hotelUser);
			
			showAlert(getStringFromResourceBundle("userinfo.successful"));
			
//			stageManager.switchScene(FxmlView.LOGIN);
    	}
    }
	
	@FXML
    private void btnCancel(ActionEvent event){
		stageManager.switchScene(FxmlView.VIEWROOMS);
	}
	

	public String getEmail() {
		return email.getText();
	}

	public String getFirstName() {
		return firstName.getText();
	}

	public String getLastName() {
		return lastName.getText();
	}

	public String getPhone() {
		return phone.getText();
	}

	public String getGender() {
		return gender.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public String getConfirmPassword() {
		return confirmPassword.getText();
	}
	
	
}
