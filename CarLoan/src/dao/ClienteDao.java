package dao;

import java.util.ArrayList;

import dao.mySQL.DatabaseConnectionException;
import dao.mySQL.ExecuteQueryException;
import entity.Cliente;

/**
 * Interfaccia per le classi che comunicheranno con un certo tipo di database 
 * per le operazioni sui clienti
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public interface ClienteDao {
	/**
	 * Inserisce il cliente ed i relativi dati nel database
	 * @param nome di tipo String: indica il nome del cliente da inserire
	 * @param cognome di tipo String: indica il cognome del cliente da inserire
	 * @param numTel di tipo String: indica il numero di telefono del cliente da inserire
	 * @param codFiscale di tipo String: indica il codice fiscale del cliente da inserire
	 * @return true se l'inserimento è avvenuto correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public boolean inserisciCliente(String nome, String cognome, String numTel, 
			String codFiscale); // throws ExecuteQueryException, DatabaseConnectionException;
	
	/**
	 * Modifica nel database i dati relativi al cliente avente codice fiscale uguale a quello
	 * in input
	 * @param codFiscale di tipo String: indica il codice fiscale del cliente da modificare
	 * @param numTel di tipo String: indica il numero di telefono del cliente da modificare
	 * @param nome di tipo String: indica il nome del cliente da modificare
	 * @param cognome di tipo String: indica il cognome del cliente da modificare
	 
	 * @return true se la modifica dei dati è avvenuta correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public boolean modificaCliente(String codFiscale, String numTel, String nome, 
			String cognome); // throws ExecuteQueryException, DatabaseConnectionException;
	
	/**
	 * Rimuove dal database il cliente identificato dal codice fiscale uguale a quello in input
	 * e i relativi dati.
	 * @param codFiscale di tipo String: indica il codice fiscale del cliente da rimuovere
	 * @return true se la rimozione è avvenuta correttamente; false altrimenti.
	 */
	public boolean rimuoviCliente(String codFiscale);
//			throws ExecuteQueryException, DatabaseConnectionException;
	
	/**
	 * Prende dal database l'insieme contenente tutti i clienti presenti al momento della query.
	 * @return ArrayList<Cliente>: vuoto se non vi sono clienti o vi sono stati problemi nell'
	 * effettuare la query, altrimenti contenente tutti i clienti presenti nel database.
	 */
	public ArrayList<Cliente> getClienti();
	//		throws ExecuteQueryException, DatabaseConnectionException;
}
