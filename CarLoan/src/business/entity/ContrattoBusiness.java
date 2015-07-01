package business.entity;

import java.util.ArrayList;
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
	public int inserisciContratto(String codiceFiscaleCliente, String targaMacchina,
			String dataInizio, String dataFine, int acconto, char tipologia,
			char tipoKm, String dittaRestituzione) {
		// TODO Auto-generated method stub
		return contratto.inserisciContratto(codiceFiscaleCliente, targaMacchina, 
				dataInizio, dataFine, acconto, tipologia, tipoKm, dittaRestituzione);
	}

	public  boolean modificaContratto(int idContratto,
			String targaMacchina, String dataInizio, String dataFIne,
			int acconto, char tipologia, char tipoKm, String dittaRestituzione) {
		// TODO Auto-generated method stub
		return contratto.modificaContratto(idContratto, targaMacchina, 
				dataInizio, dataFIne, acconto, tipologia, tipoKm, dittaRestituzione);
	}

	public boolean cancellaContratto(int idContratto) {
		// TODO Auto-generated method stub
		return contratto.cancellaContratto(idContratto);
	}
	
	public boolean chiudiContratto(int idContratto) {
		return contratto.chiudiContratto(idContratto);
	}

	public ArrayList<Contratto> getContrattiAttivi() {
		// TODO Auto-generated method stub
		return contratto.getContrattiAttivi();
	}

	public ArrayList<Contratto> getContrattiSistema() {
		// TODO Auto-generated method stub
		return contratto.getContrattiSistema();
	}

	public int getId(String targaMacchina, String dataInizio,
			String dataFIne) {
		// TODO Auto-generated method stub
		return contratto.getId(targaMacchina, dataInizio, dataFIne);
	}
	
}
