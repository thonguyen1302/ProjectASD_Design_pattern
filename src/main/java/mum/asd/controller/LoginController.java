package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import mum.asd.service.impl.AddressServiceImpl;
import mum.asd.service.impl.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import mum.asd.config.StageManager;
import mum.asd.service.HotelUserService;
import mum.asd.service.UserService;
import mum.asd.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Tan Tho Nguyen
 */

@Controller
public class LoginController implements Initializable{

	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblLogin;
    
    @Autowired
    private HotelUserService hotelUserService;


    @Autowired
	AddressServiceImpl addressService;

    @Autowired
	SampleDataService sampleDataService;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
        
	@FXML
    private void loginAction(ActionEvent event) throws IOException{
    	if(hotelUserService.authenticate(getUsername(), getPassword())){
    		    		
    		stageManager.switchScene(FxmlView.VIEWROOMS);
    		
    	}else{
    		lblLogin.setText("Login Failed.");
    	}
    }
	
	@FXML
    private void btnRegister(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.REGISTER);
    }
	
	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		String text=username.getText();
		if (!text.contains("@")){
			text = text +"@gmail.com";
		}
		return text;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sampleMethod();
	}
	public void sampleMethod(){
		sampleDataService.generateSampleData();
	}

//	@Scheduled(cron="0/2 * * * * *")
	public void printHello(){
		System.out.println("Hello World");
	}

}
