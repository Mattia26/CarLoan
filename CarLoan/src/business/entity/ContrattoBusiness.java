package business.entity;

import java.util.ArrayList;

import utility.InputController;
import dao.DaoFactory;
import dao.ContrattoDao;
import entity.Contratto;

public class ContrattoBusiness {

	private static ContrattoDao contratto;
	
	public ContrattoBusiness() {
		try {
			contratto=DaoFactory.getDaoFactory(DaoFactory.MySQL).getContrattoDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int inserisciContratto(Contratto c) {
		// TODO Auto-generated method stub
		System.out.println(InputController.stringTodate(c.getDataInizio()));
		return contratto.inserisciContratto(c.getCliente(), c.getTargaMacchina(), 
		InputController.stringTodate(c.getDataInizio()),InputController.stringTodate(c.getDataFine()), c.getQuotaAcconto(), c.getTipologia(), 
		c.getTipoChilometraggio(), c.sedeRestituzione(), c.macchinaRitirata());
	}

	public  boolean modificaContratto(Contratto c) {
		// TODO Auto-generated method stub
		return contratto.modificaContratto(c.getId(), c.getTargaMacchina(), 
				InputController.stringTodate(c.getDataInizio()),
				InputController.stringTodate(c.getDataFine()), c.getQuotaAcconto(), 
				c.getTipologia(), c.getTipoChilometraggio(), 
				c.sedeRestituzione(), c.chiuso(), c.macchinaRitirata());
	}

	public boolean cancellaContratto(int id) {
		// TODO Auto-generated method stub
		return contratto.cancellaContratto(id);
	}

	public ArrayList<Contratto> getContrattiAttivi() {
		// TODO Auto-generated method stub
		return contratto.getContrattiAttivi();
	}

	public ArrayList<Contratto> getContrattiSistema() {
		// TODO Auto-generated method stub
		return contratto.getContrattiSistema();
	}

	public int getId(Contratto c) {
		// TODO Auto-generated method stub
		return contratto.getId(c.getTargaMacchina(), InputController.stringTodate(c.getDataInizio()), InputController.stringTodate(c.getDataFine()));
	}
	
}
