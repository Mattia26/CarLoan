package business.entity;

import java.util.ArrayList;

import utility.InputController;
import dao.DaoFactory;
import dao.AutoDao;
import entity.Auto;

public class AutoBusiness {

	private static AutoDao auto;
	
	public AutoBusiness() throws InstantiationException, IllegalAccessException {
			auto=DaoFactory.getDaoFactory(DaoFactory.MySQL).getAutoDao();
	}
	
	public boolean inserisciAuto(Auto a) {
		// TODO Auto-generated method stub
		return auto.inserisciAuto(a.getModello(), a.getTarga(), a.getFascia(), a.getUltimoChilometraggio());
	}

	public boolean modificaAuto(Auto a) {
		// TODO Auto-generated method stub
		return auto.modificaAuto(a.getTarga(), a.getDataManutenzioneStraordinaria(),
				a.getDataManutenzioneOrdinaria(), a.getUltimoChilometraggio());
	}

	public boolean rimuoviAuto(String targa) {
		// TODO Auto-generated method stub
		return auto.rimuoviAuto(targa);
	}

	public ArrayList<Auto> autoDisponibili() {
		// TODO Auto-generated method stub
		return auto.getAutoDisponibili();
	}

	public ArrayList<Auto> autoSistema() {
		// TODO Auto-generated method stub
		return auto.getAutoSistema();
	}

}
