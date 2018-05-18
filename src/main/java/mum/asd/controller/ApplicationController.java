package mum.asd.controller;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mum.asd.Main;
import mum.asd.config.StageManager;
import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.view.FxmlView;

public class ApplicationController {
	public static HotelUser currentUser = null;
	
	@Lazy
    @Autowired
    public StageManager stageManager;
	
	/*
	 * Show user info
	 * Tan Tho Nguyen
	 */
	
	
	public void showUserInfo() {
		stageManager.switchScene(FxmlView.USERINFO);
	}
	

	
	/*
	 * Get String From Resource Bundle
	 * Tan Tho Nguyen
	 */
	String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
	
	
	/*
	 * Show alert
	 * Tan Tho Nguyen
	 */
	public void showAlert(String message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	
	
	/*
	 * Validations
	 * Tan Tho Nguyen
	 */
	public boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
			Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	
	public boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }
	
	public boolean validatePassword(String field, String field2){
        if(field.equals(field2)){
            return true;
        }else{
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("Password and Confirm Password are NOT match ");
        	alert.showAndWait();
            return false;            
        }
    }
	
	public void validationAlert(String field, boolean empty){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}
}
