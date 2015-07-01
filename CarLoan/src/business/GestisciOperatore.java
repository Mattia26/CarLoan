package business;

import java.util.ArrayList;

import presentation.GestioneSessione;
import entity.Operatore;
import business.entity.OperatoreBusiness;

public class GestisciOperatore {
	private OperatoreBusiness ob;
	
	public Object inserisciNuovoOperatore(ArrayList<String> operatorParameters) {
		ob = new OperatoreBusiness();
		String nome=operatorParameters.get(0);
		String cognome=operatorParameters.get(1);
		String indirizzo=operatorParameters.get(2);
		int numTelefono=Integer.parseInt(operatorParameters.get(3));
		String nickname=operatorParameters.get(4);
		return ob.inserisciOperatore(nome, cognome, indirizzo, numTelefono, nickname);
	}
	
	public Object modificaDatiOperatore(ArrayList<String> operatorParameters) {
		ob = new OperatoreBusiness();
		String nickname=GestioneSessione.getUsername();
		String nome=operatorParameters.get(0);
		String cognome=operatorParameters.get(1);
		String indirizzo=operatorParameters.get(2);
		int numTelefono=Integer.parseInt(operatorParameters.get(3));
		return ob.modificaDatiOperatore(nome, cognome, indirizzo, numTelefono, nickname);
	}
	
	public Object eliminaOperatore(String nickname) {
		ob=new OperatoreBusiness();
		return ob.rimuoviOperatore(nickname);
	}
	
	public Object getDatiOperatore() {
		Operatore o = new Operatore(GestioneSessione.getNomeOperatore(),
		GestioneSessione.getCognomeOperatore(), GestioneSessione.getIndirizzoOperatore(),
		Integer.parseInt(GestioneSessione.getTelefonoOperatore()),GestioneSessione.getUsername());
		return o;
	}
}
