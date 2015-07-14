package business;

import java.util.ArrayList;
import java.util.Iterator;

import presentation.GestioneSessione;
import entity.Operatore;
import business.entity.DatabaseInstantiationException;
import business.entity.OperatoreBusiness;

public class GestisciOperatore {
	private OperatoreBusiness ob;
	
	public GestisciOperatore() {
			try {
				ob = new OperatoreBusiness();
			} catch (DatabaseInstantiationException e) {
				// TODO Auto-generated catch block
				ob = null;
			}
	}
	
	
	public Object inserisciOperatore(ArrayList<String> operatorParameters) {
		
		try {
			String nome=operatorParameters.get(0);
			String cognome=operatorParameters.get(1);
			String indirizzo=operatorParameters.get(2);
			String numTelefono=operatorParameters.get(3);
			String nickname=operatorParameters.get(4);
			Operatore o=new Operatore(nome,cognome,indirizzo,numTelefono,nickname);
			return ob.inserisciOperatore(o);
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	
	public Object modificaOperatore(ArrayList<String> operatorParameters) {
		
		try {
		String nickname=GestioneSessione.getUsername();
		String nome=operatorParameters.get(0);
		String cognome=operatorParameters.get(1);
		String numTelefono=operatorParameters.get(2);
		String indirizzo=operatorParameters.get(3);
		
		Operatore o=new Operatore(nome,cognome,indirizzo,numTelefono,nickname);
		return ob.modificaDatiOperatore(o);
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	
	public Object eliminaOperatore(String nickname) {
		try {
			return ob.rimuoviOperatore(nickname);
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	public Object getDatiOperatore(String nickname) {
		
		try {
			ArrayList<Operatore> operatori = ob.getOperatori();
			Iterator<Operatore> it = operatori.iterator();
			while(it.hasNext()) {
				Operatore o=it.next();
				if(o.getNickname().equals(nickname))
					return o;
			}
			return new Operatore("","","","",nickname);
			
		}
		catch(NullPointerException e) {
			return new Operatore("","","","",nickname);
		}
	}
}