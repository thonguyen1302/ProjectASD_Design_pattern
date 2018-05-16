package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import mum.asd.service.impl.AddressService;
import mum.asd.service.impl.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import mum.asd.config.StageManager;
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
 * @author Sang Tran
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
    private UserService userService;


    @Autowired
	AddressService addressService;

    @Autowired
	SampleDataService sampleDataService;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
        
	@FXML
    private void loginAction(ActionEvent event) throws IOException{
    	if(userService.authenticate(getUsername(), getPassword())){
    		    		
    		stageManager.switchScene(FxmlView.USER);
    		
    	}else{
    		lblLogin.setText("Login Failed.");
    	}
    }
	
	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sampleMethod();
	}
	public void sampleMethod(){
		sampleDataService.generateSampleData();
	}

}
