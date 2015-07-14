package dao;

import java.util.ArrayList;

import dao.mySQL.DatabaseConnectionException;
import dao.mySQL.ExecuteQueryException;
import entity.Operatore;

public interface OperatoreDao {
	public boolean inserisciOperatore(String nome, String cognome, String indirizzo, 
		String numTelefono, String nickname); // throws DatabaseConnectionException, ExecuteQueryException;
	public boolean modificaDatiOperatore(String nome, String cognome, String indirizzo, 
		String numTelefono, String nickname); // throws DatabaseConnectionException, ExecuteQueryException;
	public boolean rimuoviOperatore(String nickname); 
			//throws DatabaseConnectionException, ExecuteQueryException;
	public ArrayList<Operatore> getOperatori();
			//throws ExecuteQueryException, DatabaseConnectionException;
}
