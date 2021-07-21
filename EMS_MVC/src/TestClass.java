import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.swing.*;

public class TestClass {
//	static DataBaseConnection db = new DataBaseConnection();

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		/*
		 * SecureRandom.getInstanceStrong method (available since Java 8). When this
		 * method is used, it picks up the algorithm or algorithm/provider configuration
		 * in securerandom.strongAlgorithms java.security config. By default, it is
		 * configured to use non-blocking.
		 */
//		User newUser = new User("afganp");
//		newUser.setSalt1();
//		newUser.setSalt2();
//		byte[] salt1 = newUser.getSalt1();
//		byte[] salt2 = newUser.getSalt2();
//		newUser.setHash1(salt1, "myPass");
//		newUser.setHash2(salt2, "myPass");
//		System.out.println(newUser.getHash1());
//		System.out.println(newUser.getHash2());
//		Registration reg = new Registration();
//		reg.setUserInfo();
		
		Login newLogin = new Login();

//		byte [] userSalt1Query = null;
//		try {
//			db.prst = db.con.prepareStatement("SELECT Salt1 from Users where Username=?;");
//			db.prst.setString(1, "arya_stark");
//			db.rs = db.prst.executeQuery();
//
//			while (db.rs.next()) {
//				userSalt1Query = db.rs.getBytes("Salt1");
//				System.out.print(userSalt1Query[6]);
//			}
//			
//		} catch (SQLException err) {
//			// method 'printStackTrace' prints the line number where the exception is thrown
//			err.printStackTrace(System.err);
//			// method 'getMessage()' returns the details of the thrown exception
//			System.out.println(err.getMessage());
//		}
	}

}
