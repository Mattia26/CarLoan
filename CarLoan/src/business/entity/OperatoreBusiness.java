package business.entity;

import java.util.ArrayList;
import dao.OperatoreDao;
import dao.DaoFactory;
import entity.Operatore;

public class OperatoreBusiness {

	private OperatoreDao operatore;
	
	public OperatoreBusiness() {
		try {
			operatore=DaoFactory.getDaoFactory(DaoFactory.MySQL).getOperatoreDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean inserisciOperatore(String nome, String cognome, String indirizzo, 
			int numTelefono, String nickname) {
		// TODO Auto-generated method stub
		return operatore.inserisciOperatore(nome, cognome, indirizzo, numTelefono, nickname);
	}

	public boolean modificaDatiOperatore(String nome, String cognome, String indirizzo,
			int numTelefono, String nickname) {
		// TODO Auto-generated method stub
		return operatore.modificaDatiOperatore(nome, cognome, indirizzo, numTelefono, nickname);
	}

	public boolean rimuoviOperatore(String nickname) {
		// TODO Auto-generated method stub
		return operatore.rimuoviOperatore(nickname);
	}

	public ArrayList<Operatore> getOperatori() {
		// TODO Auto-generated method stub
		return operatore.getOperatori();
	}

}
