
/**
 * 
 * @author ConstantinErmurache
 *
 */
import mvc.app.collections.models.DataBaseConnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Login extends User {

	private DataBaseConnection db = new DataBaseConnection();
	private Scanner input = new Scanner(System.in);

	private byte[] userSalt1Query, userSalt2Query;
	private String username, password, userameQuery;
	private int passwordsId;

	public Login() {
		User loginUser = new User();

		System.out.print("Username:");
		username = input.nextLine();
		System.out.print("Password: ");
		password = input.nextLine();

		// Query users.salt1 and compute hash1
		loginUser.setHash1(getUserSalt1(username), password);
		// look for computed hash1 in the database
		if (foundHash1(loginUser.getHash1())) {
			loginUser.setHash2(getUserSalt2(passwordsId), password);
			if (getUserHash2(loginUser.getHash2())) {
				System.out.println("Successful login");
			} else
				System.out.println("try again");
		}

	}

	private byte[] getUserSalt1(String username) {

		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			
			db.setPrst(db.getCon().prepareStatement("SELECT Salt1 from Users where Username=?;"));
			
			db.getPrst().setString(1, username);
			
			db.setRs(db.getPrst().executeQuery());
			
			
			while (db.getRs().next()) {
				userSalt1Query = db.getRs().getBytes("Salt1");
			}
			// start of the 'catch' block that catches SQL type of exceptions
		} catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		}
		return userSalt1Query;
	}

	private boolean getUserHash2(String hash2) {
		// Query users.hash2
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("SELECT Username from Users where Hash2=?;"));
			
			db.getPrst().setString(1, hash2);
			
			db.setRs(db.getPrst().executeQuery());
			while (db.getRs().next()) {
				userameQuery = db.getRs().getString("Username");
				return true;
			}

		} catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		}
		return false;
	}

	private boolean foundHash1(String hash1Compute) {
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("SELECT ID from Passwords where Hash1=?;"));
			
			db.getPrst().setString(1, hash1Compute);
			db.setRs(db.getPrst().executeQuery());
			while (db.getRs().next()) {
				passwordsId = db.getRs().getInt("ID");
				return true;
			}
		} catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		}
		return false;

	}

	private byte[] getUserSalt2(int id) {
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("SELECT Salt2 from Passwords where ID=?;"));
			
			db.getPrst().setInt(1, id);
			db.setRs(db.getPrst().executeQuery());
			while (db.getRs().next()) {
				userSalt2Query = db.getRs().getBytes("Salt2");
			}
		} catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		}
		return userSalt2Query;
	}
}
