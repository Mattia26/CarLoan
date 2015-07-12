package business.entity;

import java.util.ArrayList;

import dao.OperatoreDao;
import dao.DaoFactory;
import entity.Operatore;

public class OperatoreBusiness {

	private OperatoreDao operatore;
	
	public OperatoreBusiness() throws DatabaseInstantiationException {
		try {
			operatore=DaoFactory.getDaoFactory(DaoFactory.MySQL).getOperatoreDao();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			// TODO Auto-generated catch block
			throw new DatabaseInstantiationException();
		}
	}
	
	public boolean inserisciOperatore(Operatore o) {
		return operatore.inserisciOperatore(o.getNome(), o.getCognome(), o.getIndirizzo(), 
				o.getNumTelefono(), o.getNickname());
	}

	public boolean modificaDatiOperatore(Operatore o) {
		return operatore.modificaDatiOperatore(o.getNome(), o.getCognome(), o.getIndirizzo(), 
				o.getNumTelefono(), o.getNickname());
	}

	public boolean rimuoviOperatore(String nickname) {
		return operatore.rimuoviOperatore(nickname);
	}

	public ArrayList<Operatore> getOperatori() {
		return operatore.getOperatori();
	}

}
