package mvc.app.collections.views;

import java.util.Scanner;
import mvc.app.collections.controllers.*;

//17 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class RegisterView {

	private Scanner input = new Scanner(System.in);
	private String username, firstName, lastName, email, password, passwordConfirm;
	private RegisterController regControl;

	public RegisterView() {
		
	}
	public void getUserInfo() {
		System.out.println("=======================================");
		System.out.println("Wellcome to registration page:");
		System.out.println("Please complete the following fields");
		System.out.print("Username: ");
		setUsername(input.nextLine());
		System.out.print("First Name: ");
		setFirstName(input.nextLine());
		System.out.print("Last Name: ");
		setLastName(input.nextLine());
		System.out.print("Email Address: ");
		setEmail(input.nextLine());
		System.out.print("Password: ");
		setPassword(input.nextLine());
		System.out.print("Confirm Password: ");
		setPasswordConfirm(input.nextLine());
		System.out.println("=======================================");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
