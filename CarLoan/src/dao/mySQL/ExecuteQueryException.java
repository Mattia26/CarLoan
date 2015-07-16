package dao.mySQL;

/**
 * Classe per identificare errori nelle query al database;
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public class ExecuteQueryException extends Exception{
	public ExecuteQueryException() {
		
	}
	public ExecuteQueryException(String msg) {
		super(msg);
	}

}
