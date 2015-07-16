package dao.mySQL;

/**
 * Classe per identificare errori di connessione al database;
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public class DatabaseConnectionException extends Exception{
	public DatabaseConnectionException() {
		
	}

	public DatabaseConnectionException(String msg) {
		super(msg);
	}
	
}
