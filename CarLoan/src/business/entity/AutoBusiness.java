package business.entity;

import java.util.ArrayList;

import utility.InputController;
import dao.DaoFactory;
import dao.AutoDao;
import entity.Auto;
/**
 * Classe per la gestione di richieste da livello di business a livello di dao 
 * sulle operazioni relative alle auto
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public class AutoBusiness {
	/**
	 * Attributo di classe, private di tipo AutoDao.
	 */
	private static AutoDao auto;
	
	/**
	 * Costruttore: prova ad avvalorare l'attributo auto, altrimenti genera un'eccezione 
	 * di tipo DatabaseInstantiationException
	 * @throws DatabaseInstantiationException
	 */
	public AutoBusiness() throws DatabaseInstantiationException {
			try {
				auto=DaoFactory.getDaoFactory(DaoFactory.MySQL).getAutoDao();
			} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
				// TODO Auto-generated catch block
				auto=null;
				throw new DatabaseInstantiationException();
			}
	}
	
	/**
	 * Inserisce l'auto in input, tramite dao
	 * @param a di tipo auto: indica l'auto da inserire.
	 * @return true se l'auto è stata inserita, false altrimenti
	 */
	public boolean inserisciAuto(Auto a) {
		return auto.inserisciAuto(a.getModello(), a.getTarga(),a.getDataManutenzioneOrdinaria(), a.getFascia(), a.getUltimoChilometraggio());
	}

	/**
	 * Modifica l'auto in input, tramite dao
	 * @param a di tipo Auto: indica l'auto da modificare.
	 * @return true se l'auto è stata modificata correttamente a livello dao, false altrimenti
	 */
	public boolean modificaAuto(Auto a) {
		return auto.modificaAuto(a.getTarga(), InputController.stringToMySqlDate(
				a.getDataManutenzioneStraordinaria()), InputController.stringToMySqlDate(
				a.getDataManutenzioneOrdinaria()), a.getUltimoChilometraggio());
	}

	/**
	 * Rimuove l'auto identificata dalla targa in input, tramite dao
	 * @param targa di tipo String: indica la targa dell'auto da rimuovere.
	 * @return true se l'auto è stata rimossa a livello dao, false altrimenti
	 */
	public boolean rimuoviAuto(String targa) {
		return auto.rimuoviAuto(targa);
	}

	/**
	 * Prende l'insieme delle auto disponibili al momento, tramite dao 
	 * @return ArrayList<Auto>: insieme di auto disponibili al momento
	 */
	public ArrayList<Auto> autoDisponibili() {
		return auto.getAutoDisponibili();
	}

	/**
	 * Prende l'insieme delle auto presenti nel sistema al momento, tramite dao 
	 * @return ArrayList<Auto>: insieme di auto al momento
	 */
	public ArrayList<Auto> autoSistema() {
		return auto.getAutoSistema();
	}

}
