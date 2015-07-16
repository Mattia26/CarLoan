package business.entity;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.DittaDao;
import entity.Ditta;

/**
 * Classe per la gestione di richieste da livello di business a livello di dao 
 * sulle operazioni relative alle ditte
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public class DittaBusiness {
	
	/**
	 * Attributo di classe, private di tipo DittaDao.
	 */
	public static DittaDao ditta;
	
	/**
	 * Costruttore: prova ad avvalorare l'attributo ditta, altrimenti genera un'eccezione 
	 * di tipo DatabaseInstantiationException
	 * @throws DatabaseInstantiationException
	 */
	public DittaBusiness() throws DatabaseInstantiationException{
		try {
			ditta=DaoFactory.getDaoFactory(DaoFactory.MySQL).getDittaDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new DatabaseInstantiationException();
		}
	}
	
	/**
	 * Prende l'insieme delle ditte presenti nel sistema al momento, tramite dao 
	 * @return ArrayList<Ditta>: insieme delle ditte registrate nel sistema al momento
	 */
	public ArrayList<Ditta> getDitte() {
		if(ditta!=null) {
			return ditta.getDitte();	
		}
		
		else
			return new ArrayList<Ditta>();
	}
}
