package business;

import java.util.ArrayList;

import presentation.GestioneSessione;
import entity.Operatore;
import business.entity.OperatoreBusiness;

public class GestisciOperatore {
	private OperatoreBusiness ob;
	
	public GestisciOperatore() {
		try {
			ob = new OperatoreBusiness();
		} catch (InstantiationException | IllegalAccessException e) {
			ob = null;
		}
	}
	
	
	public Object inserisciOperatore(ArrayList<String> operatorParameters) {
		if(ob.equals(null))
			return false;
		
		String nome=operatorParameters.get(0);
		String cognome=operatorParameters.get(1);
		String indirizzo=operatorParameters.get(2);
		String numTelefono=operatorParameters.get(3);
		String nickname=operatorParameters.get(4);
		Operatore o=new Operatore(nome,cognome,indirizzo,numTelefono,nickname);
		return ob.inserisciOperatore(o);
	}
	
	
	public Object modificaOperatore(ArrayList<String> operatorParameters) {
		if(ob.equals(null))
			return false;
		
		String nickname=GestioneSessione.getUsername();
		String nome=operatorParameters.get(0);
		String cognome=operatorParameters.get(1);
		String indirizzo=operatorParameters.get(2);
		String numTelefono=operatorParameters.get(3);
		Operatore o=new Operatore(nome,cognome,indirizzo,numTelefono,nickname);
		return ob.modificaDatiOperatore(o);
	}
	
	
	public Object eliminaOperatore(String nickname) {
		if(ob.equals(null))
			return false;
		
		return ob.rimuoviOperatore(nickname);
	}
	
	public Object getDatiOperatore() {
		if(ob.equals(null))
			return null;
		
		Operatore o = new Operatore(GestioneSessione.getNomeOperatore(),
		GestioneSessione.getCognomeOperatore(), GestioneSessione.getIndirizzoOperatore(),
		GestioneSessione.getTelefonoOperatore(),GestioneSessione.getUsername());
		return o;
	}
}
