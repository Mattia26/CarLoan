package business.entity;

import java.util.ArrayList;

import dao.OperatoreDao;
import dao.DaoFactory;
import entity.Operatore;

public class OperatoreBusiness {

	private OperatoreDao operatore;
	
	public OperatoreBusiness() throws InstantiationException, IllegalAccessException {
		operatore=DaoFactory.getDaoFactory(DaoFactory.MySQL).getOperatoreDao();
	}
	
	public boolean inserisciOperatore(Operatore o) {
		// TODO Auto-generated method stub
		return operatore.inserisciOperatore(o.getNome(), o.getCognome(), o.getIndirizzo(), 
				o.getNumTelefono(), o.getNickname());
	}

	public boolean modificaDatiOperatore(Operatore o) {
		// TODO Auto-generated method stub
		return operatore.modificaDatiOperatore(o.getNome(), o.getCognome(), o.getIndirizzo(), 
				o.getNumTelefono(), o.getNickname());
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
