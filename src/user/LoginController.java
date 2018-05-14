package user;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	
	public void btnLogin(ActionEvent event) {
		System.out.println(userName.getText());
	}
	
	public void btnRegister(ActionEvent event) {
		System.out.println(password.getText());
	}
}
