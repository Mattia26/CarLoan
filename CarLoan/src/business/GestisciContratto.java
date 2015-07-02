package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import utility.InputController;
import entity.Cliente;
import entity.Contratto;
import business.entity.ClienteBusiness;
import business.entity.ContrattoBusiness;


public class GestisciContratto {

	private ContrattoBusiness cb;
	
	public GestisciContratto() {
		cb = new ContrattoBusiness();
	}
	
	public Object nuovoContratto(ArrayList<String> parameters) {
		GestisciCliente gc=new GestisciCliente();
		ArrayList<String> datiCliente = new ArrayList<String>();
		
		String nomeCliente = parameters.get(0);
		String cognomeCliente = parameters.get(1);
		String numTelefonoCliente = parameters.get(2);
		String cfCliente = parameters.get(3);
		String targaMacchina = parameters.get(4);
		String dataInizio = parameters.get(5);
		String dataFine = parameters.get(6);
		int acconto = Integer.parseInt(parameters.get(7));
		char tipo = parameters.get(4).charAt(0);
		char tipoKm = parameters.get(5).charAt(0);
		String dittaRestituzione = parameters.get(10);
		boolean autoRitirata;
		if(InputController.getCalendar(dataInizio).equals(LocalDate.now()))
			autoRitirata=true;
		else
			autoRitirata=false;
		
		datiCliente.add(nomeCliente);
		datiCliente.add(cognomeCliente);
		datiCliente.add(numTelefonoCliente);
		datiCliente.add(cfCliente);
		gc.inserisciCliente(datiCliente);
		
		
		Contratto c = new Contratto(-1, cfCliente, targaMacchina, dataInizio, dataFine, acconto, 
				tipo, tipoKm, dittaRestituzione, false, autoRitirata);
		return cb.inserisciContratto(c);
	}
	
	public Object modificaContratto(ArrayList<String> parameters) {
		int id = Integer.parseInt(parameters.get(0));
		String cfCliente = parameters.get(1);
		String targaMacchina = parameters.get(2);
		String dataInizio = parameters.get(3);
		String dataFine = parameters.get(4);
		int acconto = Integer.parseInt(parameters.get(5));
		char tipo = parameters.get(6).charAt(0);
		char tipoKm = parameters.get(7).charAt(0);
		String dittaRestituzione = parameters.get(8);
		
		Contratto c = new Contratto(id, cfCliente, targaMacchina, dataInizio, dataFine, acconto, 
				tipo, tipoKm, dittaRestituzione, false, false);
		
		return cb.modificaContratto(c);
		
	}
	
	
	public Object annullaContratto(String id) {
		int idC = Integer.parseInt(id);
		return cb.cancellaContratto(idC);
	}
	
	
	public Object chiudiContratto(String id) {	
		ArrayList<Contratto> contratti = cb.getContrattiAttivi();
		Iterator<Contratto> it = contratti.iterator();
		int idC=Integer.parseInt((String)id);
		
		while(it.hasNext()) {
			Contratto c = it.next();
			if(c.getId()==idC) {
				c.setChiuso(true);
				return cb.modificaContratto(c);
			}
		}
		return false;
	}
	
	
	public Object notificaRitiroAuto(String id) {
		ArrayList<Contratto> contratti = cb.getContrattiAttivi();
		Iterator<Contratto> it = contratti.iterator();
		int idC=Integer.parseInt((String)id);
		
		while(it.hasNext()) {
			Contratto c = it.next();
			if(c.getId()==idC) {
				c.setRitirata(true);
				return cb.modificaContratto(c);
			}
		}
		
		return false;
	}
	
	public Object getDatiContratto(String id) {
		int idC = Integer.parseInt(id);
		ArrayList<Contratto> listaContr = cb.getContrattiAttivi();
		Iterator<Contratto> it = listaContr.iterator();
		
		while(it.hasNext()) {
			Contratto cTemp=it.next();
			if(idC==cTemp.getId())
				return cTemp;
		}
		
		return null;	
	}
	
	
	public Object notificheSistema() {
		ArrayList<Contratto> contratti = cb.getContrattiAttivi();
		ArrayList<Contratto> contrattiFiniti = new ArrayList<Contratto>();
		Iterator<Contratto> it=contratti.iterator();
		while(it.hasNext()) {
			Contratto c = it.next();
			LocalDate dataInizio = InputController.getCalendarDB(c.getDataInizio());
			LocalDate dataFine = InputController.getCalendarDB(c.getDataFine());
			if(dataInizio.isBefore(LocalDate.now()) && !c.macchinaRitirata()) {
				c.setDataFine(LocalDate.now().toString());
				c.setChiuso(true);
				cb.modificaContratto(c);
			}
			else if(dataFine.isBefore(LocalDate.now()) && !c.chiuso())
					contrattiFiniti.add(c);
			}
		
		return contrattiFiniti;
	}
	
}
