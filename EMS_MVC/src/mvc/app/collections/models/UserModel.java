package mvc.app.collections.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//17 Jul 2021
/**
 *
 * @author cen7
 *
 */

public class UserModel {
	// Class variables
	private String username, password, firstName, lastName, email;
	private byte[] salt1, salt2;
	private String hash1, hash2;
	private int userId;
	// Class variables

	public UserModel() {
	}

	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
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

	public byte[] getSalt1() {
		return salt1;
	}

	public void setSalt1() {
		salt1 = saltSpawn();
	}

	public byte[] getSalt2() {
		return salt2;
	}

	public void setSalt2() {
		salt2 = saltSpawn();
	}

	public String getHash1() {
		return hash1;
	}

	public void setHash1(byte[] salt, String password) {

		hash1 = hashGenerate(salt, password);
	}

	public String getHash2() {
		return hash2;
	}

	public void setHash2(byte[] salt, String password) {
		hash2 = hashGenerate(salt, password);
	}

	
	private byte[] saltSpawn() {
		// https://www.veracode.com/blog/research/cryptographically-secure-pseudo-random-number-generator-csprng
		byte[] salt = new byte[64];
		try {
			SecureRandom nativePrngNon = SecureRandom.getInstanceStrong();
			nativePrngNon.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salt;

	}

	private String hashGenerate(byte[] salt, String password) {
		// create MessageDigest instance of SHA-256
		String saltString = "";
		String hash = "";

		for (int i = 0; i < salt.length; i++) {
			saltString += salt[i];
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(salt);
			byte[] bytes = messageDigest.digest((password + saltString).getBytes());
			// convert bytes[] to hexadecimal format
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hash = stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;

	}
}
