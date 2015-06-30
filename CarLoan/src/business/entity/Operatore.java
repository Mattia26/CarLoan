package business.entity;

import java.util.ArrayList;
import java.util.HashMap;

import dao.OperatoreDao;
import dao.DaoFactory;

public class Operatore {

	private static OperatoreDao operatore;
	
	Operatore() {
		try {
			operatore=DaoFactory.getDaoFactory(DaoFactory.MySQL).getOperatoreDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean inserisciOperatore(String nome, String cognome, int età,
			String nickname) {
		// TODO Auto-generated method stub
		return operatore.inserisciOperatore(nome, cognome, età, nickname);
	}

	public boolean modificaDatiOperatore(String nome, String cognome, int età,
			String nickname) {
		// TODO Auto-generated method stub
		return operatore.modificaDatiOperatore(nome, cognome, età, nickname);
	}

	public boolean rimuoviOperatore(String nickname) {
		// TODO Auto-generated method stub
		return operatore.rimuoviOperatore(nickname);
	}

	public ArrayList<HashMap<String, String>> getOperatori() {
		// TODO Auto-generated method stub
		return operatore.getOperatori();
	}

}
