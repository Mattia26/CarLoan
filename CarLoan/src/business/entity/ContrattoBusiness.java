package business.entity;

import java.util.ArrayList;

import utility.InputController;
import dao.DaoFactory;
import dao.ContrattoDao;
import entity.Contratto;

/**
 * Classe per la gestione di richieste da livello di business a livello di dao 
 * sulle operazioni relative ai contratti
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public class ContrattoBusiness {
	/**
	 * Attributo di classe, private di tipo ContrattoDao.
	 */
	private static ContrattoDao contratto;
	
	/**
	 * Costruttore: prova ad avvalorare l'attributo contratto, altrimenti genera un'eccezione 
	 * di tipo DatabaseInstantiationException
	 * @throws DatabaseInstantiationException
	 */
	public ContrattoBusiness() throws DatabaseInstantiationException {
		try {
			contratto=DaoFactory.getDaoFactory(DaoFactory.MySQL).getContrattoDao();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			// TODO Auto-generated catch block
			throw new DatabaseInstantiationException();
		}
	}
	
	/**
	 * Inserisce il contratto in input, tramite dao
	 * @param c di tipo Contratto: indica il contratto da inserire.
	 * @return true se il contratto è stato inserito, false altrimenti
	 */
	public int inserisciContratto(Contratto c) {
		
		return contratto.inserisciContratto(c.getCliente(), c.getTargaMacchina(), 
		InputController.stringToMySqlDate(c.getDataInizio()),
		InputController.stringToMySqlDate(c.getDataFine()), c.getQuotaAcconto(), 
		c.getTipologia(), c.getTipoChilometraggio(), c.sedeRestituzione(), 
		c.macchinaRitirata());
	}

	/**
	 * Modifica l'contratto in input, tramite dao
	 * @param a di tipo Contratto: indica l'contratto da modificare.
	 * @return true se il contratto è stato modificato a livello dao, false altrimenti
	 */
	public  boolean modificaContratto(Contratto c) {
		
		return contratto.modificaContratto(c.getId(), c.getTargaMacchina(), 
				InputController.stringToMySqlDate(c.getDataInizio()),
				InputController.stringToMySqlDate(c.getDataFine()), c.getQuotaAcconto(), 
				c.getTipologia(), c.getTipoChilometraggio(), 
				c.sedeRestituzione(), c.chiuso(), c.macchinaRitirata());
	}

	/**
	 * Rimuove l'contratto identificata dall'id in input, tramite dao
	 * @param id di tipo int: indica l'id del contratto da rimuovere.
	 * @return true se il contratto è stato rimosso a livello dao, false altrimenti
	 */
	public boolean cancellaContratto(int id) {
		return contratto.cancellaContratto(id);
	}

	/**
	 * Prende l'insieme dei contratti attivi nel sistema al momento, tramite dao 
	 * @return ArrayList<Contratto>: insieme di contratti attivi al momento
	 */
	public ArrayList<Contratto> getContrattiAttivi() {
		return contratto.getContrattiAttivi();
	}

	/**
	 * Prende l'insieme dei contratti presenti nel sistema al momento, tramite dao 
	 * @return ArrayList<Contratto>: insieme di contratti al momento
	 */
	public ArrayList<Contratto> getContrattiSistema() {
		return contratto.getContrattiSistema();	
	}
	
}
