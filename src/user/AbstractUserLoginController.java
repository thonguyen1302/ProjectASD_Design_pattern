package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class AbstractUserLoginController {
	// Declare variables that represent elements on the form	
	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	
	//	Add event for button Login
	public abstract void btnLogin(ActionEvent event);
	
	//	Add event for button Register
	public abstract void btnRegister(ActionEvent event);

	public TextField getUserName() {
		return userName;
	}

	public void setUserName(TextField userName) {
		this.userName = userName;
	}

	public TextField getPassword() {
		return password;
	}

	public void setPassword(TextField password) {
		this.password = password;
	}
	
	
}

