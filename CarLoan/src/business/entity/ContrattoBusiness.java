package business.entity;

import java.util.ArrayList;

import utility.InputController;
import dao.DaoFactory;
import dao.ContrattoDao;
import entity.Contratto;

public class ContrattoBusiness {

	private static ContrattoDao contratto;
	
	public ContrattoBusiness() throws DatabaseInstantiationException {
		try {
			contratto=DaoFactory.getDaoFactory(DaoFactory.MySQL).getContrattoDao();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			// TODO Auto-generated catch block
			throw new DatabaseInstantiationException();
		}
	}
	
	
	public int inserisciContratto(Contratto c) {
		
		return contratto.inserisciContratto(c.getCliente(), c.getTargaMacchina(), 
		InputController.stringToMySqlDate(c.getDataInizio()),
		InputController.stringToMySqlDate(c.getDataFine()), c.getQuotaAcconto(), 
		c.getTipologia(), c.getTipoChilometraggio(), c.sedeRestituzione(), 
		c.macchinaRitirata());
	}

	
	public  boolean modificaContratto(Contratto c) {
		
		return contratto.modificaContratto(c.getId(), c.getTargaMacchina(), 
				InputController.stringToMySqlDate(c.getDataInizio()),
				InputController.stringToMySqlDate(c.getDataFine()), c.getQuotaAcconto(), 
				c.getTipologia(), c.getTipoChilometraggio(), 
				c.sedeRestituzione(), c.chiuso(), c.macchinaRitirata());
	}

	
	public boolean cancellaContratto(int id) {
		return contratto.cancellaContratto(id);
	}

	
	public ArrayList<Contratto> getContrattiAttivi() {
		return contratto.getContrattiAttivi();
	}

	
	public ArrayList<Contratto> getContrattiSistema() {
		return contratto.getContrattiSistema();	
	}

	
	public int getId(Contratto c) {

		return contratto.getId(c.getTargaMacchina(), 
				InputController.stringToMySqlDate(c.getDataInizio()), 
				InputController.stringToMySqlDate(c.getDataFine()));
	}
	
}
