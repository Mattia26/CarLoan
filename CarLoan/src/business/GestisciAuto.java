package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import utility.InputController;
import business.entity.AutoBusiness;
import business.entity.ContrattoBusiness;
import entity.Auto;
import entity.Contratto;

public class GestisciAuto {
	
	private AutoBusiness car;
	
	public GestisciAuto() {
		car = new AutoBusiness();
	}
	
	public Object inserisciAuto(ArrayList<String> parameters) {
		Auto a;
		String targa = parameters.get(0);
		String modello = parameters.get(1);
		char fascia = parameters.get(2).charAt(0);
		boolean inManutenzione = Boolean.parseBoolean(parameters.get(3));
		String dataManutenzioneOrd = parameters.get(4);
		double ultimoKm = Double.parseDouble(parameters.get(5));
		a = new Auto(modello, targa, fascia, inManutenzione, dataManutenzioneOrd, ultimoKm);
		return car.inserisciAuto(a);
	}
	
	public Object modificaAuto(ArrayList<String> parameters) {
		Auto a;
		String targa = parameters.get(0); // non modificabile!
		String modello = parameters.get(1);
		char fascia = parameters.get(2).charAt(0);
		boolean inManutenzione = Boolean.parseBoolean(parameters.get(3)); //gestito già da inserisciInManutenzione... non modificabile?
		String dataManutenzioneOrd = parameters.get(4); 
		double ultimoKm = Double.parseDouble(parameters.get(5)); //metterlo non modificabile?
		a = new Auto(modello, targa, fascia, inManutenzione, dataManutenzioneOrd, ultimoKm);
		return car.modificaAuto(a);
	}
	
	public Object eliminaAuto(String parameter) {
		return car.rimuoviAuto(parameter);
	}
	
	public Object cercaAuto(ArrayList<String> parameters){
		 ArrayList<Auto> auto = new  ArrayList<Auto>();
		 ArrayList<Contratto> contratti = new ArrayList<Contratto>();
		ContrattoBusiness contratto = new ContrattoBusiness();
		auto = car.autoSistema();
		contratti = contratto.getContrattiAttivi();
		
			
			LocalDate dataInizio = InputController.getCalendar(parameters.get(1));
			LocalDate dataFine = InputController.getCalendar(parameters.get(2));
			
			Iterator<Contratto> it = contratti.iterator();
			
			while(it.hasNext()){
				Contratto corrente = it.next();
				LocalDate dataInizioContratto = InputController.getCalendar(corrente.getDataInizio());
				LocalDate dataFineContratto = InputController.getCalendar(corrente.getDataFine());
				
				if((dataInizio.isAfter(dataInizioContratto) && dataInizio.isBefore(dataFineContratto)) ||
					(dataFine.isAfter(dataInizioContratto) && dataFine.isBefore(dataFineContratto) )||
					(dataInizio.isBefore(dataInizioContratto) && dataFine.isAfter(dataFineContratto))){
					
					Iterator<Auto> itr = auto.iterator();
					
					while(itr.hasNext()){
						Auto corr = itr.next();
						if(corr.getTarga().equals(corrente.getTargaMacchina())){
							auto.remove(corr);
						    break;
						}
					}		
				}
			}
			
			if(parameters.get(0) != null){
				Iterator<Auto> iter = auto.iterator();
				while(iter.hasNext()){
					Auto current = iter.next();
					if(current.getFascia() != parameters.get(0).charAt(0))
						auto.remove(current);
				}
			}
		
		return auto;
	}
	
	
	public Object inserisciInManutenzione(String parameter) {
		ArrayList<Auto> autoSistema = car.autoDisponibili();
		Iterator<Auto> it = autoSistema.iterator();
		while(it.hasNext()) {
			Auto a = it.next();
			if(a.getTarga().equals(parameter)) {
				a.setInManutenzione(true);
				return car.modificaAuto(a);
			}
		}
		return false;
	}
	
	public Object fineManutenzione(String parameter) {
		ArrayList<Auto> autoSistema = car.autoSistema();
		Iterator<Auto> it = autoSistema.iterator();
		while(it.hasNext()) {
			Auto a = it.next();
			if(a.getTarga().equals(parameter)) {
				a.setInManutenzione(false);
				return car.modificaAuto(a);
			}
		}
		return false;
	}
	
	
	public Object inserisciNuovoChilometraggio(Auto a) {
		return car.modificaAuto(a);
	}
	
	public Object getDatiAuto(String parameter) throws ObjectNotFoundException {
		ArrayList<Auto> autoSistema = car.autoSistema();
		Iterator<Auto> it = autoSistema.iterator();
		while(it.hasNext()) {
			Auto a = it.next();
			if(a.getTarga().equals(parameter)) {
				return a;
			}
		}
		throw new ObjectNotFoundException();
	}
	
	public Object getAutoSistema() {
		return car.autoSistema();
	}
}
