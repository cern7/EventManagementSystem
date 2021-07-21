import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;

public class Registration {

	private DataBaseConnection db = new DataBaseConnection();
	private Scanner input = new Scanner(System.in);
	private User newUser;
	private String password;

	public Registration() {
	}

	private void dbUserInfoUpdate() {
		try {
			int id = 0;
			// query the userId from the database
			db.prst = db.con.prepareStatement("SELECT UserID from Users where Username=?;");
			db.prst.setString(1, newUser.getUsername());
			db.rs = db.prst.executeQuery();
			while (db.rs.next()) {
				id = db.rs.getInt("UserID");
			}
			// preparing the statement for inserting the data into the database
			db.prst = db.con
					.prepareStatement("UPDATE UserInfo SET FirstName=?, Lastname=?, EmailAddress=? WHERE ID=?;");
			db.prst.setString(1, newUser.getFirstName());
			db.prst.setString(2, newUser.getLastName());
			db.prst.setString(3, newUser.getEmail());
			db.prst.setInt(4, id);

			db.prst.executeUpdate();
			db.prst.clearParameters();

		} // start of the 'catch' block that catches SQL type of exceptions
		catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		}
	}

	private void dbUsersUpdate() {
		try {
			// preparing the statement for inserting the data into the database
			db.prst = db.con.prepareStatement("INSERT INTO Users (Username, Hash2, Salt1) VALUES(?,?,?);");
			db.prst.setString(1, newUser.getUsername());

			newUser.setSalt2();
			newUser.setHash2(newUser.getSalt2(), password);
			db.prst.setString(2, newUser.getHash2());

			newUser.setSalt1();
			db.prst.setBytes(3, newUser.getSalt1());

			// executing the query
			db.prst.executeUpdate();
			db.prst.clearParameters();

			db.prst = db.con.prepareStatement("INSERT INTO Passwords (Hash1, Salt2) VALUES(?,?);");

			newUser.setHash1(newUser.getSalt1(), password);
			db.prst.setString(1, newUser.getHash1());

			db.prst.setBytes(2, newUser.getSalt2());

			db.prst.executeUpdate();
			db.prst.clearParameters();

		} // start of the 'catch' block that catches SQL type of exceptions
		catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		}
	}

	public void setUserInfo() {
		System.out.println("Wellcome to registration page:");
		System.out.println("Please complete the following fields");
		System.out.print("Username: ");
		String username = input.nextLine();

		while (!availableUsername(username)) {
			System.out.print("The username is already in use\nUsername: ");
			username = input.nextLine();
		}

		newUser = new User(username);

		System.out.print("First Name: ");
		String firstName = input.nextLine();
		System.out.print("Last Name: ");
		String lastName = input.nextLine();
		System.out.print("Email Address: ");
		String email = input.nextLine();

		while (!validEmail(email)) {
			System.out.print("Email is not valid, try again\nEmail Address: ");
			email = input.nextLine();
		}
		System.out.print("Password: ");
		password = input.nextLine();
		System.out.print("Confirm Password: ");
		String passwordConfirm = input.nextLine();
		while (!password.equals(passwordConfirm) || !validPassword(password)) {
			System.out.println("===============================");
			System.out.println("Password should contain: ");
			System.out.println("- between 8 and 20 characters");
			System.out.println("- at least one upper case alphabet character");
			System.out.println("- at least one lower case alphabet character");
			System.out.println("- at least one special character which includes !@#$%&*()-+=^");
			System.out.println("- no white space");
			System.out.println("===============================");
			System.out.println("Invalid password, try again");
			System.out.print("Password: ");
			password = input.nextLine();
			System.out.print("Confirm Password: ");
			passwordConfirm = input.nextLine();
		}
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);

		dbUsersUpdate();
		dbUserInfoUpdate();
	}

	private boolean availableUsername(String username) {
		boolean available = true;
		try {
			db.prst = db.con.prepareStatement("SELECT userID from Users where username=?;");
			db.prst.setString(1, username);
			db.rs = db.prst.executeQuery();
			if (db.rs.next()) {
				available = false;
			}
		} catch (SQLException err) {
			// 'printStackTrace()' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		}
		return available;
	}

	private boolean validEmail(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		// Compile regular expression to get the pattern
		Pattern pattern = Pattern.compile(regex);
		// Create instance of matcher
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();

	}

	private boolean validPassword(String password) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		// Compile regular expression to get the pattern
		Pattern pattern = Pattern.compile(regex);

		if (password == null)
			return false;

		// Create instance of matcher
		Matcher matcher = pattern.matcher(password);

		return matcher.matches();
	}
}
