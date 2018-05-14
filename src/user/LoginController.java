package user;

import javafx.event.*;

public class LoginController extends AbstractUserLoginController {
	//	Add event for button Login
	public void btnLogin(ActionEvent event) {
		System.out.println(getUserName().getText());
	}
	
	//	Add event for button Register
	public void btnRegister(ActionEvent event) {
		System.out.println(getPassword().getText());
	}
}
