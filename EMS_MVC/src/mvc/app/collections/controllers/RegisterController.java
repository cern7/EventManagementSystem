package mvc.app.collections.controllers;

import mvc.app.collections.views.RegisterView;
import mvc.app.collections.models.RegisterLogic;

//17 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class RegisterController {

	private RegisterLogic regLogic;
	private RegisterView regView = new RegisterView();;

	public RegisterController() {
		regView.getUserInfo();
		regLogic = new RegisterLogic(regView.getUsername(), regView.getPassword(), regView.getPasswordConfirm(),
				regView.getFirstName(), regView.getLastName(), regView.getEmail());
	}
}
