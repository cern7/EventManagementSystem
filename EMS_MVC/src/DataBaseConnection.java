import java.sql.*;

/*
 * https://thezaz.com/blog/storing_passwords_securely_wit
 * database security design
 */
public class DataBaseConnection {
	static final String Driver = "org.sqlite.JDBC";
	static final String DataBaseURL = "jdbc:sqlite:eventSystemDataBase";

	/*
	 * Using 'protected' modifier makes the class instances accesible to its
	 * subclass only
	 */
	protected Connection con = null;
	protected Statement st = null;
	protected ResultSet rs = null;
	protected ResultSetMetaData md = null;
	protected PreparedStatement prst = null;

	public DataBaseConnection() {
		try {
			Class.forName(Driver);
			con = DriverManager.getConnection(DataBaseURL);
		} catch (Exception err) {
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
}
