package business;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.temporal.ChronoUnit;

import utility.InputController;
import entity.Auto;
import entity.Cliente;
import entity.Contratto;
import entity.ListinoPrezzi;
import business.entity.AutoBusiness;
import business.entity.ClienteBusiness;
import business.entity.ContrattoBusiness;
import business.entity.DatabaseInstantiationException;
import business.entity.ListinoBusiness;


public class GestisciContratto {

	private ContrattoBusiness cb;
	
	public GestisciContratto() {
		try {
			cb = new ContrattoBusiness();
		} catch (DatabaseInstantiationException e) {
			// TODO Auto-generated catch block
			cb = null;
		}
	}
	
	public Object Initialize(String s){
		
		
		LocalDate yesterday = LocalDate.now().minusDays(1);
		ArrayList<Contratto> ritorno = new ArrayList<Contratto>();
		
		try{
			ArrayList<Contratto> contratti = cb.getContrattiSistema();
			Iterator<Contratto> it = contratti.iterator();	
			while(it.hasNext()){
				
				Contratto current = it.next();
				if(InputController.getDate(current.getDataFine()).equals(yesterday) 
						&& !current.chiuso()) {
					ritorno.add(current);
				}
			}
			
			GestisciAuto g = new GestisciAuto();
			g.Initialize();
		}
		
		catch(NullPointerException e) {
			return new ArrayList<Contratto>();
		}
		
		return ritorno;
	}
	
	public Object nuovoContratto(ArrayList<String> parameters) {
		
		try {
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
			if(InputController.getDate(dataInizio).equals(LocalDate.now()))
				autoRitirata=true;
			else
				autoRitirata=false;
		
			datiCliente.add(nomeCliente);
			datiCliente.add(cognomeCliente);
			datiCliente.add(numTelefonoCliente);
			datiCliente.add(cfCliente);
			gc.inserisciCliente(datiCliente);
		
			Contratto c = new Contratto(0, cfCliente, targaMacchina, dataInizio, dataFine, 
					acconto, tipo, tipoKm, dittaRestituzione, false, autoRitirata);
			return cb.inserisciContratto(c);
		}
			catch(NullPointerException e) {
				return -1;
			}
	}
	
	public Object modificaContratto(ArrayList<String> parameters) {
		try {
			String dataInizio = parameters.get(2);
		
			if(ChronoUnit.DAYS.between(LocalDate.now(),InputController.getDate(dataInizio)) < 3)
				return false;
		
			int id = Integer.parseInt(parameters.get(0));
			String cfCliente = parameters.get(9);
			String targaMacchina = parameters.get(1);
			String dataFine = parameters.get(3);
			int acconto = Integer.parseInt(parameters.get(10));
			char tipo = parameters.get(5).charAt(0);
			char tipoKm = parameters.get(6).charAt(0);
			String dittaRestituzione = parameters.get(4);
		
			Contratto c = new Contratto(id, cfCliente, targaMacchina, dataInizio, dataFine, 
				acconto, tipo, tipoKm, dittaRestituzione, false, false);
			return cb.modificaContratto(c);
		}
		catch(NullPointerException e) {
			
			return false;
		}
		
	}
	
	
	public Object annullaContratto(String id) {
		
		int idC = Integer.parseInt(id);
		try {
			return cb.cancellaContratto(idC);
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	
	
	public Object chiudiContratto(String id) {	
		try {
			try {
				Contratto c = (Contratto) getDatiContratto(id);
				c.setChiuso(true);
				return cb.modificaContratto(c);
			} 
			catch (ObjectNotFoundException e) {
				return false;
			}
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	
	
	public Object notificaRitiroAuto(String id) {
		
		try {
			Contratto c;
			try {
				c = (Contratto) getDatiContratto(id);
				if(c.macchinaRitirata())
					return false;
			
				c.setRitirata(true);
				return cb.modificaContratto(c);
			} 
			catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
				return false;
			}
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	
	
	public Object getDatiContratto(String id) throws ObjectNotFoundException {
		try {
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
		catch(NullPointerException e) {
			return null;
		}
	}
	
	
	public Object calcolaImporto(ArrayList<String> parameters) {
		
		try {
			ListinoBusiness lb;	
			int costoTipo;
			int costoTipoKm;
			double kmPercorsi;
			long durataContratto;

			String id=parameters.get(0);
			double nuovoKm=Double.parseDouble(parameters.get(1));
			Contratto c;
			try {
				c = (Contratto) getDatiContratto(id);
				if(InputController.getDate(c.getDataInizio()).isAfter(LocalDate.now()))
					return -1.0;
			
				Character tipo = c.getTipologia();
				Character tipoKm = c.getTipoChilometraggio();
				GestisciAuto g = new GestisciAuto();
				Auto a = (Auto) g.getDatiAuto(c.getTargaMacchina());
				Character fascia = a.getFascia();
				kmPercorsi = nuovoKm - a.getUltimoChilometraggio();
				a.setUltimoChilometraggio(nuovoKm);
				g.inserisciNuovoChilometraggio(a);
				lb = new ListinoBusiness();
		
				if(tipo.equals('G') && tipoKm.equals('L')) {
					costoTipo = lb.getPrezzi().get(0);
					costoTipoKm = lb.getPrezzi().get(2);
					durataContratto = ChronoUnit.DAYS.between(
						InputController.getDate(c.getDataInizio()), LocalDate.now() );
				
					return costoTipo*durataContratto + costoTipoKm*(kmPercorsi/50) 
							- c.getQuotaAcconto(); 
			}
				else if(tipo.equals('G') && tipoKm.equals('I')) {
					costoTipo = lb.getPrezzi().get(0);
					costoTipoKm = lb.getPrezzi().get(3);
					durataContratto = ChronoUnit.DAYS.between(LocalDate.now(),
						InputController.getDate(c.getDataInizio()));
				
					return costoTipo*durataContratto + costoTipoKm*kmPercorsi 
							- c.getQuotaAcconto();
			}
				
				else if(tipo.equals('S') && tipoKm.equals('L')) {
					costoTipo = lb.getPrezzi().get(1);
					costoTipoKm = lb.getPrezzi().get(2);
					durataContratto = ChronoUnit.WEEKS.between(LocalDate.now(),
						InputController.getDate(c.getDataInizio()));
				
					return costoTipo*durataContratto + costoTipoKm*(kmPercorsi/50)
							- c.getQuotaAcconto();	
			}
			
				else {
					costoTipo = lb.getPrezzi().get(1);
					costoTipoKm = lb.getPrezzi().get(3);
					durataContratto = ChronoUnit.WEEKS.between(LocalDate.now(),
							InputController.getDate(c.getDataInizio()));
				
					return costoTipo*durataContratto + costoTipoKm*kmPercorsi 
							- c.getQuotaAcconto();
				}
			} catch (ObjectNotFoundException | NullPointerException e) {
			// TODO Auto-generated catch block
			return -1.0;
			}
		
		}
		catch(NullPointerException e) {
			return -1.0;
		}
		
	}
	
	
	public Object notificheSistema() {
		try {
		
			ArrayList<Contratto> contratti = cb.getContrattiAttivi();
			ArrayList<Contratto> contrattiFiniti = new ArrayList<Contratto>();
			Iterator<Contratto> it=contratti.iterator();
			while(it.hasNext()) {
				Contratto c = it.next();
				LocalDate dataInizio = InputController.getDate(c.getDataInizio());
				LocalDate dataFine = InputController.getDate(c.getDataFine());
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
		catch(NullPointerException e) {
			return new ArrayList();
		}
	}
}
