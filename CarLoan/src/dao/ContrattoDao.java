package dao;

import java.util.ArrayList;
import entity.Contratto;

/**
 * Interfaccia per le classi che comunicheranno con un certo tipo di database 
 * per le operazioni sui contratti
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public interface ContrattoDao {
	
	/**
	 * Inserisce il contratto nel database
	 * @param codFiscaleCliente di tipo String: indica il codice fiscale del cliente che
	 * stipula il contratto da inserire
	 * @param targaMacchina di tipo String: indica la targa dell'auto da noleggiare 
	 * per il contratto da inserire
	 * @param dataInizio di tipo String: indica la data di inizio del contratto da inserire
	 * @param dataFine di tipo String: indica la data di fine del contratto da inserire
	 * @param acconto di tipo int: indica la quota dell'acconto versato al momento di stipula
	 * del contratto da inserire
	 * @param tipologia di tipo char: indica la tipologia del contratto stipulato da inserire
	 * @param tipoChilometraggio di tipo char: indica il tipo di chilometraggio
	 *  del contratto stipulato da inserire
	 * @param ditta di tipo String: indica la sede di restituzione scelta 
	 * per il contratto stipulato
	 * @param macchinaRitirata di tipo boolean: indica se l'auto è stata già ritirata al momento
	 * di stipula del contratto(macchinaRitirata=true) o meno(macchinaRitirata=false)
	 * @return true se l'inserimento è avvenuto correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public int inserisciContratto(String codFiscaleCliente, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoChilometraggio, String ditta, boolean macchinaRitirata);
	
	/**
	 * Modifica nel database il contratto identificato dall'id in input
	 * @param idContratto di tipo int: indica l'id del contratto da modificare
	 * @param targaMacchina di tipo String: indica la targa dell'auto da noleggiare
	 * relativa al contratto da modificare
	 * @param dataInizio di tipo String: indica la data di inizio del contratto da modificare
	 * @param dataFine di tipo String: indica la data di fine del contratto da modificare
	 * @param acconto di tipo int: indica la quota dell'acconto versato al momento della stipula
	 * o, eventualmente venga modificato, al momento della modifica
	 * @param tipologia di tipo char: indica la tipologia del contratto da modificare
	 * @param tipoChilometraggio di tipo char: indica il tipo di chilometraggio
	 *  del contratto da modificare
	 * @param ditta di tipo String: indica la sede di restituzione scelta 
	 * per il contratto da modificare
	 * @param macchinaRitirata di tipo boolean: indica se l'auto è stata ritirata al momento
	 * della modifica(macchinaRitirata=true) o meno(macchinaRitirata=false)
	 * @return true se la modifica è avvenuta correttamente nel database;
	 * false altrimenti
	 * 
	 */
	public boolean modificaContratto(int idContratto, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoChilometraggio, String ditta, boolean chiuso, boolean macchinaRitirata);
	
	/**
	 * Rimuove dal database il contratto identificato dall'id in input.
	 * @param idContratto di tipo int: indica l'id del contratto da rimuovere
	 * @return true se è stato rimosso correttamente il contratto avente id uguale a quello
	 * in input, false altrimenti.
	 */
	public boolean cancellaContratto(int idContratto);
	
	/**
	 * Prende dal database l'insieme di tutti i contratti che sono in corso al momento di 
	 * esecuzione della query.
	 * @return ArrayList<Contratto>: vuoto se non vi sono contratti attivi al momento di
	 * esecuzione della query o se vi sono stati problemi con la query, contenente l'insieme
	 * dei contratti attivi al momento della query altrimenti.
	 */
	public ArrayList<Contratto> getContrattiAttivi();
	
	/**
	 * Prende dal database l'insieme di tutti i contratti presenti al momento di 
	 * esecuzione della query.
	 * @return ArrayList<Contratto>: vuoto se non vi sono contratti al momento di
	 * esecuzione della query o se vi sono stati problemi con la query, contenente l'insieme
	 * dei contratti presenti nel database al momento della query altrimenti.
	 */
	public ArrayList<Contratto> getContrattiSistema();
	
	/**
	 * Restituisce l'id relativo al contratto avente data inizio, data fine e targa dell'auto
	 * uguali ai parametri in input.
	 * @param targaMacchina di tipo String: indica la targa dell'auto noleggiata 
	 * alla stipula del contratto
	 * @param dataInizio: indica la data di inizio del contratto
	 * @param dataFine: indica la data di fine del contratto
	 * @return int: -1 se non vi è alcun contratto legato a quei parametri in input o se
	 * vi sono stati problemi nella query, indicante l'id del contratto altrimenti.
	 */
	public int getId(String targaMacchina, String dataInizio, String dataFine);

}
