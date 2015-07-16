package utility;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;



public class LoginUtility implements Serializable{

	static HashMap<String, String> i=getCurrentUserList(); 
	
	public boolean insertUser(String username, String password) { 
		if(!containsUser(username)) {
			i.put(username, cryptPassword(password));
			if(salva())
				return true;
			else 
				return false;
		}
		else return false;
	}

	public boolean containsUser(String username) { 
		return i.containsKey(username);
	}
	
	public boolean deleteUser(String username) {
		try {
			
			if(i.remove(username).equals(""))
				return false;
			else {
				salva();
				return true;
			}
		}
		
		catch (NullPointerException e) {
			return false;
		}
	}

	private String getPassword(String username) { 
		return i.get(username); 
	}

	public boolean correctPassword(String username, String password) { 
		if(this.containsUser(username))   
			return (cryptPassword(password).equals(this.getPassword(username)));
		else return false;
	}

	private String cryptPassword(String password)
	   {
	   try {
	   	String passwordCrypted="";
	   	MessageDigest digest = MessageDigest.getInstance("MD5");
	   	byte[] b=digest.digest(password.getBytes());
	   	for(int i=0;i<b.length;i++) {
	   	Byte bi=b[i];
	   	passwordCrypted +=bi.toString();
	   	
	   	}
	   	return passwordCrypted;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "password";
		}
	   }

	private static HashMap<String, String> getCurrentUserList() {
		try {
			return LoginFileUtility.carica();
		}
		catch(ClassNotFoundException | IOException e) { 
			return new HashMap<String, String>();
		}
	}

	private boolean salva() {
		try {
			LoginFileUtility.salva(i);
			return true;
		} 
		catch (IOException e) {
		return false;
		}
	}

}