package dao;

import java.util.ArrayList;

import dao.mySQL.DatabaseConnectionException;
import dao.mySQL.ExecuteQueryException;
import entity.Auto;

public interface AutoDao {
	/**
	 * Inserisce l'auto nel database
	 * @param modello di tipo String: indica il modello dell'auto da inserire
	 * @param targa di tipo String: indica la targa dell'auto da inserire
	 * @param data_man_ord di tipo String: indica la data della prossima manutenzione ordinaria
	 * @param fascia di tipo char: indica la fascia di appartenenza dell'auto
	 * @param km di tipo double: indica l'ultimo chilometraggio associato all'auto
	 * @return true se l'inserimento è avvenuto correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public boolean inserisciAuto(String modello, String targa,String data_man_ord, char fascia, 
			double km); // throws ExecuteQueryException, DatabaseConnectionException;
	
	/**
	 * Modifica nel database l'auto avente targa uguale alla targa in input
	 * @param targa di tipo String: indica la targa dell'auto da modificare
	 * @param inizioManutenzioneStraordinaria di tipo String: 
	 * indica la nuova data della manutenzione straordinaria da inserire nel database per l'auto. 
	 * @param dataManutenzioneOrdinaria di tipo String: indica la nuova data di manutenzione
	 * ordinaria da inserire nel database per l'auto
	 * @param km di tipo double: indica l'ultimo chilometraggio da inserire per l'auto.
	 * @return true se la modifica è avvenuta correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public boolean modificaAuto(String targa, String inizioManutenzioneStraordinaria,
			String dataManutenzioneOrdinaria, double km);
				//	throws ExecuteQueryException, DatabaseConnectionException;
	/**
	 * Rimuove dal database l'auto avente targa uguale alla targa in input
	 * @param targa di tipo String: indica la targa dell'auto da rimuovere
	 * @return true se l'auto è stata rimossa correttamente dal database;
	 * false altrimenti
	 * 
	 */
	public boolean rimuoviAuto(String targa); 
	//		throws ExecuteQueryException, DatabaseConnectionException;;
	/**
	 * Prende dal database l'insieme contenente tutte le auto disponibili al momento di
	 * somministrazione della query.
	 * @return ArrayList<Auto> vuoto se non vi sono auto disponibili al momento; altrimenti 
	 * un ArrayList contenente tutte le auto disponibili al momento.
	 * 
	 */
	public ArrayList<Auto> getAutoDisponibili();
		//	throws ExecuteQueryException, DatabaseConnectionException;;
	/**
	 * Prende dal database l'insieme contenente tutte le auto presenti nel sistema
	 *  al momento di somministrazione della query.
	 * @return ArrayList<Auto> vuoto se non vi sono auto nel database o se vi sono stati
	 * problemi nell'esecuzione della query o nella connessione; altrimenti 
	 * un ArrayList contenente tutte le auto presenti nel database.
	 * 
	 */
	public ArrayList<Auto> getAutoSistema();
		//	throws ExecuteQueryException, DatabaseConnectionException;
}
