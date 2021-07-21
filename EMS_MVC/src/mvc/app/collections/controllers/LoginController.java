package mvc.app.collections.controllers;

import mvc.app.collections.views.LoginView;
import mvc.app.collections.models.LoginLogic;

//17 Jul 2021
/**
 *
 *@author cen7
 *
 */
public class LoginController {
	private LoginView logView;
	private LoginLogic loginUser;
	
	public LoginController() {
		logView = new LoginView();
		loginUser = new LoginLogic(logView.getUsername(), logView.getPassword());
		// gets UserID for using it in events logic
		loginUser.getUserId();
	}
}
