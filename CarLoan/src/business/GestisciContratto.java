package business;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;

import utility.InputController;
import entity.Auto;
import entity.Cliente;
import entity.Contratto;
import entity.ListinoPrezzi;
import business.entity.AutoBusiness;
import business.entity.ClienteBusiness;
import business.entity.ContrattoBusiness;
import business.entity.ListinoBusiness;


public class GestisciContratto {

	private ContrattoBusiness cb;
	
	public GestisciContratto() {
		cb = new ContrattoBusiness();
	}
	
	public Object nuovoContratto(ArrayList<String> parameters) {
		GestisciCliente gc=new GestisciCliente();
		ArrayList<String> datiCliente = new ArrayList<String>();
		
		String nomeCliente = parameters.get(6);
		String cognomeCliente = parameters.get(7);
		String numTelefonoCliente = parameters.get(10);
		String cfCliente = parameters.get(8);
		String targaMacchina = parameters.get(0);
		String dataInizio = parameters.get(1);
		String dataFine = parameters.get(2);
		int acconto = Integer.parseInt(parameters.get(9));
		char tipo = parameters.get(4).charAt(0);
		char tipoKm = parameters.get(5).charAt(0);
		String dittaRestituzione = parameters.get(3);
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
		
		
		Contratto c = new Contratto(0, cfCliente, targaMacchina, dataInizio, dataFine, acconto, 
				tipo, tipoKm, dittaRestituzione, false, autoRitirata);
		return cb.inserisciContratto(c);
	}
	
	public Object modificaContratto(ArrayList<String> parameters) {
		int id = Integer.parseInt(parameters.get(0));
		String cfCliente = parameters.get(9);
		String targaMacchina = parameters.get(1);
		String dataInizio = parameters.get(2);
		String dataFine = parameters.get(3);
		int acconto = Integer.parseInt(parameters.get(10));
		char tipo = parameters.get(5).charAt(0);
		char tipoKm = parameters.get(6).charAt(0);
		String dittaRestituzione = parameters.get(4);
		
		Contratto c = new Contratto(id, cfCliente, targaMacchina, dataInizio, dataFine, acconto, 
				tipo, tipoKm, dittaRestituzione, false, false);
		
		
		return cb.modificaContratto(c);
		
	}
	
	
	public Object annullaContratto(String id) {
		int idC = Integer.parseInt(id);
		return cb.cancellaContratto(idC);
	}
	
	
	
	public Object chiudiContratto(String id) {	
		Contratto c;
		try {
			c = (Contratto) getDatiContratto(id);
			c.setChiuso(true);
			return cb.modificaContratto(c);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	
	
	public Object notificaRitiroAuto(String id) {
		Contratto c;
		try {
			c = (Contratto) getDatiContratto(id);
			c.setRitirata(true);
			return cb.modificaContratto(c);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	
	
	public Object getDatiContratto(String id) throws ObjectNotFoundException {
		int idC = Integer.parseInt(id);
		ArrayList<Contratto> listaContr = cb.getContrattiAttivi();
		Iterator<Contratto> it = listaContr.iterator();
		
		while(it.hasNext()) {
			Contratto cTemp=it.next();
			if(idC==cTemp.getId())
				return cTemp;
		}
		
		throw new ObjectNotFoundException();
	}
	
	
	public Object calcolaImporto(ArrayList<String> parameters) {
		ListinoBusiness lb;
		
		int costoTipo;
		int costoTipoKm;
		double kmPercorsi;
		int durataContratto;

		String id=parameters.get(0);
		double nuovoKm=Double.parseDouble(parameters.get(2));
		Contratto c;
		try {
			c = (Contratto) getDatiContratto(id);
			char tipo = c.getTipologia();
			char tipoKm = c.getTipoChilometraggio();
			GestisciAuto g = new GestisciAuto();
			Auto a = (Auto) g.getDatiAuto(c.getTargaMacchina());
			kmPercorsi = nuovoKm - a.getUltimoChilometraggio();
			g.inserisciNuovoChilometraggio(a);
			
			lb = new ListinoBusiness();
		
			if(tipoKm=='G' && tipo=='L') {
				costoTipo = lb.getPrezzi().get(0);
				costoTipoKm = lb.getPrezzi().get(2);
				durataContratto = Period.between(InputController.getCalendar(c.getDataInizio())
						, LocalDate.now()).getDays();
				
				return costoTipo*durataContratto + costoTipoKm*33; 
				// 33 da sostituire con parametro di fascia(es 50 km, 100 km etc.);
			}
			else if(tipoKm == 'G' && tipo == 'I') {
				costoTipo = lb.getPrezzi().get(0);
				costoTipoKm = lb.getPrezzi().get(3);
				durataContratto = Period.between(InputController.getCalendar(c.getDataInizio())
						, LocalDate.now()).getDays();
				
				return costoTipo*durataContratto + costoTipoKm*kmPercorsi;
			}
				
			else if(tipo== 'S' && tipoKm == 'L') {
				costoTipo = lb.getPrezzi().get(1);
				costoTipoKm = lb.getPrezzi().get(2);
				durataContratto = Period.between(InputController.getCalendar(c.getDataInizio())
						, LocalDate.now()).getDays() / 7;
				
				return costoTipo*durataContratto + costoTipoKm*33;	
			}
			
			else {
				costoTipo = lb.getPrezzi().get(1);
				costoTipoKm = lb.getPrezzi().get(3);
				durataContratto = Period.between(InputController.getCalendar(c.getDataInizio())
						, LocalDate.now()).getDays() / 7;
				
				return costoTipo*durataContratto + costoTipoKm*kmPercorsi;
			}
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			return -1;
		}	
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
