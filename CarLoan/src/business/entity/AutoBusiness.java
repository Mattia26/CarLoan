package business.entity;

import java.util.ArrayList;
import dao.DaoFactory;
import dao.AutoDao;
import entity.Auto;

public class AutoBusiness {

	private static AutoDao auto;
	
	public AutoBusiness() {
		try {
			auto=DaoFactory.getDaoFactory(DaoFactory.MySQL).getAutoDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean inserisciAuto(String modello, String targa, char fascia, 
			double ultimoChilometraggio) {
		// TODO Auto-generated method stub
		return auto.inserisciAuto(modello, targa, fascia, ultimoChilometraggio);
	}

	public boolean modificaAuto(String targa, boolean inManutenzione, 
			String dataManutenzioneOrdinaria, double ultimoChilometraggio) {
		// TODO Auto-generated method stub
		return auto.modificaAuto(targa, inManutenzione, 
				dataManutenzioneOrdinaria, ultimoChilometraggio);
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
