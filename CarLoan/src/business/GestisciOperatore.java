package business;

import java.util.ArrayList;
import java.util.Iterator;

import presentation.GestioneSessione;
import entity.Operatore;
import business.entity.DatabaseInstantiationException;
import business.entity.OperatoreBusiness;

public class GestisciOperatore {
	
	/**
	 * Attributo privato di tipo OperatoreBusiness
	 */
	private OperatoreBusiness ob;
	
	/**
	 * Costruttore: prova ad istanziare l'attributo ob; altrimenti lo setta a null
	 */
	public GestisciOperatore() {
			try {
				ob = new OperatoreBusiness();
			} catch (DatabaseInstantiationException e) {
				// TODO Auto-generated catch block
				ob = null;
			}
	}
	
	/**
	 * Metodo per l'inserimento di un operatore
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * l'inserimento dell'operatore
	 * @return true se l'operatore è stato inserito correttamente; false altrimenti
	 */
	public Object inserisciOperatore(ArrayList<String> operatorParameters) {
		if(ob==null)
			return false;
		
		String nome=operatorParameters.get(0);
		String cognome=operatorParameters.get(1);
		String indirizzo=operatorParameters.get(2);
		String numTelefono=operatorParameters.get(3);
		String nickname=operatorParameters.get(4);
		
		Operatore o=new Operatore(nome,cognome,indirizzo,numTelefono,nickname);
		return ob.inserisciOperatore(o);	
	}
	
	/**
	 * Metodo per la modifica dei dati di un operatore
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * la modifica dell'operatore
	 * @return true se i dati dell'operatore sono stati modificati correttamente; 
	 * false altrimenti
	 */
	public Object modificaOperatore(ArrayList<String> operatorParameters) {
		if(ob==null)
			return false;
		
		String nickname=GestioneSessione.getUsername();
		String nome=operatorParameters.get(0);
		String cognome=operatorParameters.get(1);
		String numTelefono=operatorParameters.get(2);
		String indirizzo=operatorParameters.get(3);
		
		Operatore o=new Operatore(nome,cognome,indirizzo,numTelefono,nickname);
		if(
				((Operatore)getDatiOperatore(nickname)).getNome().equals("") &&
				((Operatore)getDatiOperatore(nickname)).getCognome().equals("") &&
				((Operatore)getDatiOperatore(nickname)).getIndirizzo().equals("") &&
				((Operatore)getDatiOperatore(nickname)).getNumTelefono().equals("") ) 
			
			return ob.inserisciOperatore(o);
		
		else
			return ob.modificaDatiOperatore(o);
		
	}
	
	/**
	 * Metodo per la rimozione di un operatore e relativi dati dal sistema.
	 * @param nickname di tipo String: indica il nickname dell'operatore da rimuovere
	 * @return true se l'operatore è stato rimosso correttamente; false altrimenti
	 */
	public Object eliminaOperatore(String nickname) {
		if(ob==null)
			return false;
		
		return ob.rimuoviOperatore(nickname);
	}
	
	/**
	 * Metodo che, a partire dalla stringa in input indicante il nickname, 
	 * restituisce i dati dell'operatore associato a quel nickname.
	 * @param parameter di tipo String: indica il nickname dell'operatore
	 * @return Operatore avente nickname uguale alla stringa in input, se è presente 
	 * nel sistema; un operatore privo di informazioni relative al profilo altrimenti.
	 * @throws ObjectNotFoundException
	 */
	public Object getDatiOperatore(String nickname) {
		if(ob==null)
			return new Operatore("","","","",nickname);
		
		ArrayList<Operatore> operatori = ob.getOperatori();
		Iterator<Operatore> it = operatori.iterator();
		while(it.hasNext()) {
			Operatore o=it.next();
			if(o.getNickname().equals(nickname))
				return o;
		}
		
		return new Operatore("","","","",nickname);
		}
}