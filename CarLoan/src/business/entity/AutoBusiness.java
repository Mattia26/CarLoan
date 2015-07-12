package business.entity;

import java.util.ArrayList;

import utility.InputController;
import dao.DaoFactory;
import dao.AutoDao;
import entity.Auto;

public class AutoBusiness {

	private static AutoDao auto;
	
	public AutoBusiness() throws DatabaseInstantiationException {
			try {
				auto=DaoFactory.getDaoFactory(DaoFactory.MySQL).getAutoDao();
			} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
				// TODO Auto-generated catch block
				throw new DatabaseInstantiationException();
			}
	}
	
	public boolean inserisciAuto(Auto a) {
		return auto.inserisciAuto(a.getModello(), a.getTarga(),a.getDataManutenzioneOrdinaria(), a.getFascia(), a.getUltimoChilometraggio());
	}

	public boolean modificaAuto(Auto a) {
		return auto.modificaAuto(a.getTarga(), InputController.stringToMySqlDate(
				a.getDataManutenzioneStraordinaria()), InputController.stringToMySqlDate(
				a.getDataManutenzioneOrdinaria()), a.getUltimoChilometraggio());
	}

	public boolean rimuoviAuto(String targa) {
		return auto.rimuoviAuto(targa);
	}

	public ArrayList<Auto> autoDisponibili() {
		return auto.getAutoDisponibili();
	}

	public ArrayList<Auto> autoSistema() {
		return auto.getAutoSistema();
	}

}
