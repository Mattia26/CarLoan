package business.entity;

import java.util.ArrayList;

import dao.OperatoreDao;
import dao.DaoFactory;
import entity.Operatore;

/**
 * Classe per la gestione di richieste da livello di business a livello di dao 
 * sulle operazioni relative agli operatori
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public class OperatoreBusiness {
	/**
	 * Attributo di classe, private di tipo OperatoreDao.
	 */
	private static OperatoreDao operatore;
	
	/**
	 * Costruttore: prova ad avvalorare l'attributo operatore, altrimenti genera un'eccezione 
	 * di tipo DatabaseInstantiationException
	 * @throws DatabaseInstantiationException
	 */
	public OperatoreBusiness() throws DatabaseInstantiationException {
		try {
			operatore=DaoFactory.getDaoFactory(DaoFactory.MySQL).getOperatoreDao();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			// TODO Auto-generated catch block
			throw new DatabaseInstantiationException();
		}
	}
	
	/**
	 * Inserisce l' operatore in input, tramite dao
	 * @param c di tipo Operatore: indica l' operatore da inserire.
	 * @return true se l' operatore è stato inserito, false altrimenti
	 */
	public boolean inserisciOperatore(Operatore o) {
		return operatore.inserisciOperatore(o.getNome(), o.getCognome(), o.getIndirizzo(), 
				o.getNumTelefono(), o.getNickname());
	}

	/**
	 * Modifica l'operatore in input, tramite dao
	 * @param a di tipo Operatore: indica l'operatore da modificare.
	 * @return true se l' operatore è stato modificato a livello dao, false altrimenti
	 */
	public boolean modificaDatiOperatore(Operatore o) {
		return operatore.modificaDatiOperatore(o.getNome(), o.getCognome(), o.getIndirizzo(), 
				o.getNumTelefono(), o.getNickname());
	}

	/**
	 * Rimuove l'operatore identificato dal nickname in input, tramite dao
	 * @param nickname di tipo String: indica il nickname dell'operatore da rimuovere.
	 * @return true se l' operatore è stato rimosso a livello dao, false altrimenti
	 */
	public boolean rimuoviOperatore(String nickname) {
		return operatore.rimuoviOperatore(nickname);
	}

	/**
	 * Prende l'insieme degli operatori attivi nel sistema al momento, tramite dao 
	 * @return ArrayList<Operatore>: insieme degli operatori registrati nel sistema al momento
	 */
	public ArrayList<Operatore> getOperatori() {
		return operatore.getOperatori();
	}

}
