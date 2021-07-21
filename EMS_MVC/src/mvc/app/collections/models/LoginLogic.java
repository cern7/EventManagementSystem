package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//17 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class LoginLogic {
	private DataBaseConnection db = new DataBaseConnection();

	private Scanner input = new Scanner(System.in);
	private UserModel logUser = new UserModel();

	private byte[] userSalt1Query, userSalt2Query;
	private int id;

	public LoginLogic() {
	}

	public LoginLogic(String username, String password) {
		isLoggedIn(username, password);
	}
	public boolean isLoggedIn(String username, String password) {
		// Query users.salt1 and compute hash1 for login user
		logUser.setHash1(getUserSalt1(username), password);
		// look for computed hash1 in the database
		if (foundHash1(logUser.getHash1())) {
			// Query users.salt2 and compute hash2 for login user
			logUser.setHash2(getUserSalt2(getId()), password);
			// look for computed hash2 in the database
			if (getUserHash2(logUser.getHash2())) {
				queryUserId(username);
				return true;
			} else
				System.out.println("try again");
		}
		return false;
	}

	public int getUserId() {
		return logUser.getUserId();
	}

	public void setUserId(int userId) {
		logUser.setUserId(userId);
	}

	private void queryUserId(String username) {
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));

			db.setPrst(db.getCon().prepareStatement("SELECT UserID from Users where Username=?;"));

			db.getPrst().setString(1, username);

			db.setRs(db.getPrst().executeQuery());

			while (db.getRs().next()) {
				logUser.setUserId(db.getRs().getInt("UserID"));

			}
			// start of the 'catch' block that catches SQL type of exceptions
		} catch (SQLException err) {
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

	private byte[] getUserSalt1(String username) {

		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));

			db.setPrst(db.getCon().prepareStatement("SELECT Salt1 from Users where Username=?;"));

			db.getPrst().setString(1, username);

			db.setRs(db.getPrst().executeQuery());

			while (db.getRs().next()) {
				setUserSalt1Query(db.getRs().getBytes("Salt1"));
			}
			// start of the 'catch' block that catches SQL type of exceptions
		} catch (SQLException err) {
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
		return getUserSalt1Query();
	}

	private boolean getUserHash2(String hash2) {
		// Query users.hash2
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("SELECT Username from Users where Hash2=?;"));

			db.getPrst().setString(1, hash2);

			db.setRs(db.getPrst().executeQuery());
			while (db.getRs().next()) {
				return true;
			}

		} catch (SQLException err) {
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
		return false;
	}

	private boolean foundHash1(String hash1Compute) {
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("SELECT ID from Passwords where Hash1=?;"));

			db.getPrst().setString(1, hash1Compute);
			db.setRs(db.getPrst().executeQuery());
			while (db.getRs().next()) {
				setId(db.getRs().getInt("ID"));
				return true;
			}
		} catch (SQLException err) {
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
		return false;

	}

	private byte[] getUserSalt2(int id) {
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("SELECT Salt2 from Passwords where ID=?;"));

			db.getPrst().setInt(1, id);
			db.setRs(db.getPrst().executeQuery());
			while (db.getRs().next()) {
				setUserSalt2Query(db.getRs().getBytes("Salt2"));
			}
		} catch (SQLException err) {
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
		return getUserSalt2Query();
	}

	private byte[] getUserSalt1Query() {
		return userSalt1Query;
	}

	private void setUserSalt1Query(byte[] userSalt1Query) {
		this.userSalt1Query = userSalt1Query;
	}

	private byte[] getUserSalt2Query() {
		return userSalt2Query;
	}

	private int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setUserSalt2Query(byte[] userSalt2Query) {
		this.userSalt2Query = userSalt2Query;
	}
}
