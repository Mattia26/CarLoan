package dao;

import java.util.ArrayList;
import entity.Ditta;

/**
 * Interfaccia per le classi che comunicheranno con un certo tipo di database 
 * per le operazioni sulle ditte
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public interface DittaDao {
	/**
	 * Prende dal database l'insieme contenente tutte le ditte 
	 * presenti al momento della query.
	 * @return ArrayList<Ditta>: vuoto se non vi sono ditte nel database
	 *  o vi sono stati problemi nell'effettuare la query, 
	 *  altrimenti contenente tutte le ditte presenti nel database.
	 */
	public ArrayList<Ditta> getDitte();
}
