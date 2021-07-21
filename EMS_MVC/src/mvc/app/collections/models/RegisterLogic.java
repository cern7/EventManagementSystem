package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mvc.app.collections.controllers.RegisterController;

//17 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class RegisterLogic {
	private DataBaseConnection db = new DataBaseConnection();
	private Scanner input = new Scanner(System.in);
	private UserModel newUser = new UserModel();

	public RegisterLogic(String username, String password, String passwordConfirm, String firstName, String lastName,
			String email) {
		while (!availableUsername(username)) {
			System.out.print("Username already in use\nTry again: ");
			username = input.nextLine();
		}
		newUser.setUsername(username);

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
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		while (!validEmail(email)) {
			System.out.print("Invalid email, try again:");
			email = input.nextLine();
		}
		newUser.setEmail(email);
		newUser.setSalt1();
		newUser.setSalt2();
		newUser.setHash1(newUser.getSalt1(), newUser.getPassword());
		newUser.setHash2(newUser.getSalt2(), newUser.getPassword());
		dbUsersUpdate();
		dbUserInfoUpdate();
	}

	private void dbUserInfoUpdate() {
		try {
			int id = 0;
			// query the userId from the database
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));

			db.setPrst(db.getCon().prepareStatement("SELECT UserID from Users where Username=?;"));

			db.getPrst().setString(1, newUser.getUsername());

			db.setRs(db.getPrst().executeQuery());

			while (db.getRs().next()) {
				id = db.getRs().getInt("UserID");
			}
			// preparing the statement for inserting the data into the database
			db.setPrst(db.getCon()
					.prepareStatement("UPDATE UserInfo SET FirstName=?, Lastname=?, EmailAddress=? WHERE ID=?;"));

			db.getPrst().setString(1, newUser.getFirstName());
			db.getPrst().setString(2, newUser.getLastName());
			db.getPrst().setString(3, newUser.getEmail());
			db.getPrst().setInt(4, id);

			db.getPrst().executeUpdate();
			db.getPrst().clearParameters();

		} // start of the 'catch' block that catches SQL type of exceptions
		catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		} finally {
			if (db.getRs() != null) {
				try {
					db.getRs().close();
				} catch (SQLException err) {
				}
			}
			if (db.getPrst() != null) {
				try {
					db.getPrst().close();
				} catch (SQLException err) {
				}
			}
			if (db.getCon() != null) {
				try {
					db.getCon().close();
				} catch (SQLException err) {
				}
			}
		}
	}

	private void dbUsersUpdate() {
		try {
			// preparing the statement for inserting the data into the database
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("INSERT INTO Users (Username, Hash2, Salt1) VALUES(?,?,?);"));

			db.getPrst().setString(1, newUser.getUsername());
			db.getPrst().setString(2, newUser.getHash2());
			db.getPrst().setBytes(3, newUser.getSalt1());

			// executing the query
			db.getPrst().executeUpdate();
			db.getPrst().clearParameters();

			db.setPrst(db.getCon().prepareStatement("INSERT INTO Passwords (Hash1, Salt2) VALUES(?,?);"));

			db.getPrst().setString(1, newUser.getHash1());
			db.getPrst().setBytes(2, newUser.getSalt2());

			db.getPrst().executeUpdate();
			db.getPrst().clearParameters();

		} // start of the 'catch' block that catches SQL type of exceptions
		catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		} finally {
			if (db.getRs() != null) {
				try {
					db.getRs().close();
				} catch (SQLException err) {
				}
			}
			if (db.getPrst() != null) {
				try {
					db.getPrst().close();
				} catch (SQLException err) {
				}
			}
			if (db.getCon() != null) {
				try {
					db.getCon().close();
				} catch (SQLException err) {
				}
			}
		}
	}

	private boolean availableUsername(String username) {
		boolean available = true;
		try {
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("SELECT userID from Users where username=?;"));

			db.getPrst().setString(1, username);
			db.setRs(db.getPrst().executeQuery());

			if (db.getRs().next()) {
				available = false;
			}
		} catch (SQLException err) {
			// 'printStackTrace()' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		} finally {
			if (db.getRs() != null) {
				try {
					db.getRs().close();
				} catch (SQLException err) {
				} 
			}
			if (db.getPrst() != null) {
				try {
					db.getPrst().close();
				} catch (SQLException err) {
				}
			}
			if (db.getCon() != null) {
				try {
					db.getCon().close();
				} catch (SQLException err) {
				}
			}
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
