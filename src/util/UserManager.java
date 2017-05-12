package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

	private SQLConnector sql = new SQLConnector();
	
	public void addUser(String email, String password) throws NoSuchAlgorithmException
	{
		ResultSet rs = sql.getData("SELECT COUNT(id) FROM users WHERE email = '" + email + "'");
		
		try {
			if ( !rs.next() ) {
				sql.sendQuery("INSERT INTO users VALUES('0', '" + email + "', '" + hashPassword(password) + "')");
			}
		} catch (SQLException e) {
			System.out.println("Error @ addUser: " + e.getMessage());
		}
	}
	
	public boolean checkUserLogin(String email, String password) {
		ResultSet rs = sql.getData("SELECT password FROM users WHERE email = '" + email + "'");
		
		try {
			if ( rs.next() ) {
				if ( rs.getString(1).equals(hashPassword(password)) ) {
					return true;
				} else {
					System.out.println("Invalid password");
					return false;
				}
			} else {
				System.out.println("ResultSet is empty");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error @ checkUserLogin: " + e.getMessage());
		}
		
		return false;
	}
	
	public String hashPassword(String password) throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(password.getBytes());

	    byte byteData[] = md.digest();

	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++)
	        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

	    return sb.toString();
	}
	
	public int getUserIdByEmail(String email) {
		
		ResultSet rs = sql.getData("SELECT id FROM users WHERE email = '" + email + "'");
		
		try {
			if ( rs.next() ) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error @ getUserById: " + e.getMessage());
		}
		return -1;	
	}
	
	public boolean userAlreadyExist(String email) {
		ResultSet rs = sql.getData("SELECT id FROM users WHERE email = '" + email + "'");
		try {
			return !rs.next();
		} catch (SQLException e) {
			System.out.println("Error @ userALreadyExist: " + e.getMessage());
		}
		return true;
	}
}
