package business;

import java.util.ArrayList;
import java.util.Iterator;
import entity.Contratto;
import business.entity.ContrattoBusiness;


public class GestisciContratto {

	private ContrattoBusiness cb;
	
	public Object nuovoContratto(ArrayList<String> parameters) {
		String cfCliente = parameters.get(0);
		String targaMacchina = parameters.get(1);
		String dataInizio = parameters.get(2);
		String dataFine = parameters.get(3);
		int acconto = Integer.parseInt(parameters.get(4));
		char tipo = parameters.get(4).charAt(0);
		char tipoKm = parameters.get(5).charAt(0);
		String dittaRestituzione = parameters.get(6);
		return cb.inserisciContratto(cfCliente, targaMacchina, dataInizio, dataFine, 
				acconto, tipo, tipoKm, dittaRestituzione);
	}
	
	public Object annullaContratto(String id) {
		int idC = Integer.parseInt(id);
		return cb.cancellaContratto(idC);
	}
	
	public Object cercaContratto(String id) {
		int idC = Integer.parseInt(id);
		ArrayList<Contratto> listaContr;
		Iterator<Contratto> it;
		cb=new ContrattoBusiness();
		listaContr=cb.getContrattiAttivi();
		it = listaContr.iterator();
		
		while(it.hasNext()) {
			Contratto cTemp=it.next();
			if(idC==cTemp.getId())
				return cTemp;
		}
		return null;
		
	}
	
	public Object chiudiContratto(String id) {	
		int idC=Integer.parseInt((String)id);
		cb=new ContrattoBusiness();
		return cb.chiudiContratto(idC);
	}
}
