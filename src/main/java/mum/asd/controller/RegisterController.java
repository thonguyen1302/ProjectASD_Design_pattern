package mum.asd.controller;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mum.asd.config.StageManager;
import mum.asd.domain.Address;
import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.service.AddressService;
import mum.asd.service.HotelUserService;
import mum.asd.service.UserService;
import mum.asd.view.FxmlView;

/**
 * @author Tan Tho Nguyen
 */

@Controller
public class RegisterController extends ApplicationController implements Initializable{
	
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
	
	@FXML
    private TextField street;
	
    @FXML
    private TextField city;
	
    @FXML
    private TextField state;
	
    @FXML
    private TextField zipcode;
	
	@FXML
    private Button btnLogin;
	
	@FXML
    private Button btnRegister;
	
	@Autowired
	private HotelUserService hotelUserService;
	
	@Autowired
	private AddressService addressService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private void register(ActionEvent event){

    	if(validate("Email", getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
    	   validate("First Name", getFirstName(), "[a-zA-Z]+") &&
    	   validate("Last Name", getLastName(), "[a-zA-Z]+") && 
    	   emptyValidation("Password", getPassword().isEmpty()) &&
    	   emptyValidation("Confirm Password", getConfirmPassword().isEmpty()) &&
    	   validatePassword(getPassword(), getConfirmPassword())
    	   ){
    
			HotelUser hotelUser = new HotelUser();
			hotelUser.setFirstName(getFirstName());
			hotelUser.setLastName(getLastName());
			hotelUser.setEmail(getEmail());
			hotelUser.setPassword(getPassword());
			
			Address address = new Address(getStreet(), getCity(), getState(), getZipcode());
			addressService.save(address);
			
			hotelUser.setAddress(address);
			
			hotelUserService.save(hotelUser);
			
			showAlert(getStringFromResourceBundle("register.successful"));
			
			stageManager.switchScene(FxmlView.LOGIN);
    	}
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

	public String getStreet() {
		return street.getText();
	}

	public String getCity() {
		return city.getText();
	}

	public String getState() {
		return state.getText();
	}

	public String getZipcode() {
		return zipcode.getText();
	}
	
}
