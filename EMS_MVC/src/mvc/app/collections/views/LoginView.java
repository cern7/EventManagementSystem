package mvc.app.collections.views;
import java.util.Scanner;

import mvc.app.collections.controllers.*;
//17 Jul 2021
/**
 *
 *@author cen7
 *
 */
public class LoginView {
	private Scanner input = new Scanner(System.in);
	private String username, password;
	
	public LoginView() {
		System.out.println("Login Page");
		System.out.println("================================");
		System.out.print("Username:");
		setUsername(input.nextLine());
		System.out.print("Password: ");
		setPassword(input.nextLine());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
