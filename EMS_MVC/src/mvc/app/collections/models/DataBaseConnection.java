// 17 Jul 2021
/**
 * 
 * @author ConstantinErmurache
 *
 */
package mvc.app.collections.models;

import java.sql.*;

/*
 * https://thezaz.com/blog/storing_passwords_securely_wit
 * database security design
 */
public class DataBaseConnection {
	static final String Driver = "org.sqlite.JDBC";
	static final String DataBaseURL = "jdbc:sqlite:eventSystemDataBase";

	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null; //
	private ResultSetMetaData md = null;
	private PreparedStatement prst = null;

	public Statement getSt() {
		return st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public ResultSetMetaData getMd() {
		return md;
	}

	public Connection getCon() {
		return con;
	}

	public PreparedStatement getPrst() {
		return prst;
	}

	public void setPrst(PreparedStatement prst) {
		this.prst = prst;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void setMd(ResultSetMetaData md) {
		this.md = md;
	}

	public static String getDatabaseurl() {
		return DataBaseURL;
	}

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
