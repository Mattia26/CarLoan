package dao;

import java.util.ArrayList;

import dao.mySQL.DatabaseConnectionException;
import dao.mySQL.ExecuteQueryException;
import entity.Operatore;

/**
 * Interfaccia per le classi che comunicheranno con un certo tipo di database 
 * per le operazioni sugli operatori
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public interface OperatoreDao {
	
	/**
	 * Inserisce l'operatore ed i relativi dati nel database
	 * @param nome di tipo String: indica il nome dell'operatore da inserire
	 * @param cognome di tipo String: indica il cognome dell'operatore da inserire
	 * @param indirizzo di tipo String: indica l'indirizzo dell'operatore da inserire
	 * @param numTelefono di tipo String: indica il numero di telefono dell'operatore da inserire
	 * @param nickname di tipo String: indica il nickname associato all'operatore da inserire
	 * @return true se l'inserimento è avvenuto correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public boolean inserisciOperatore(String nome, String cognome, String indirizzo, 
		String numTelefono, String nickname); // throws DatabaseConnectionException, ExecuteQueryException;
	
	/**
	 * Modifica nel database i dati relativi all'operatore identificato 
	 * dal nickname uguale a quello in input
	 * @param nome di tipo String: indica il nome dell'operatore da modificare
	 * @param cognome di tipo String: indica il cognome dell'operatore da modificare
	 * @param indirizzo di tipo String: indica l'eventuale nuovo indirizzo dell'operatore
	 * @param numTelefono di tipo String: indica l'eventuale nuovo numero di telefono dell'operatore
	 * @param nickname di tipo String: indica il nickname associato all'operatore da modificare
	 * @return true se la modifica è avvenuto correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public boolean modificaDatiOperatore(String nome, String cognome, String indirizzo, 
		String numTelefono, String nickname); // throws DatabaseConnectionException, ExecuteQueryException;
	
	/**
	 * Rimuove dal database l'operatore identificato dal nickname in input e i relativi dati
	 * @param nickname di tipo String: indica il nickname associato all'operatore da rimuovere
	 * @return true se la rimozione dal database è avvenuta correttamente; false altrimenti
	 */
	public boolean rimuoviOperatore(String nickname); 
			//throws DatabaseConnectionException, ExecuteQueryException;
	
	/**
	 * Prende dal database l'insieme contenente tutti gli operatori 
	 * presenti al momento della query.
	 * @return ArrayList<Operatore>: vuoto se non vi sono operatori nel database
	 *  o vi sono stati problemi nell'effettuare la query, 
	 *  altrimenti contenente tutti gli operatori presenti nel database.
	 */
	public ArrayList<Operatore> getOperatori();
			//throws ExecuteQueryException, DatabaseConnectionException;
}
