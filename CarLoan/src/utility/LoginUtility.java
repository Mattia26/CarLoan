package utility;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;



public class LoginUtility implements Serializable{

	static HashMap<String, String> i=getCurrentUserList(); // contiene la map contenente 
		// username, password e indirizzi email degli utenti registrati
	
	public void insertUser(String username, String password) { 
		// aggiunge un utente indicando username e password, quindi salva la map nel file
	i.put(username, cryptPassword(password));
	salva();
	}

	public boolean containsUser(String username) { 
		//verifica se l'user in input è già contenuto nella map
		return i.containsKey(username);
	}

	private String getPassword(String username) { 
		// restituisce un vettore contenente la password dell'username in input
		return i.get(username); 
	}

	public boolean correctPassword(String username, String password) { 
		// verifica la correttezza della password ricevuta, per l'username in input
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
			// carica il file se esiste
		try {
			return LoginFileUtility.carica();
		}
		catch(ClassNotFoundException | IOException e) { // altrimenti crea una nuova map
			return new HashMap<String, String>();
		}
	}

	private boolean salva() { // salva il file
		try {
			LoginFileUtility.salva(i);
			return true;
		} 
		catch (IOException e) {
		return false;
		}
	}

}