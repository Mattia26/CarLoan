package business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
		try {
			car = new AutoBusiness();
		} catch (InstantiationException | IllegalAccessException e) {
			car = null;
		}
	}
	
	public void Initialize(){
		ArrayList<Auto> auto = new ArrayList<Auto>();
		LocalDate today = LocalDate.now();
		
		auto = car.autoSistema();
		Iterator<Auto> it = auto.iterator();
		
		while(it.hasNext()){
			Auto a = it.next();
			LocalDate manutenzioneO =  InputController.getDate
					(a.getDataManutenzioneOrdinaria());
			LocalDate manutenzioneS = InputController.getDate
					(a.getDataManutenzioneOrdinaria());
			if( manutenzioneO.plusDays(2).equals(today)){
				a.setDataManutenzioneOrdinaria(
						InputController.getString(InputController.getDate(a.getDataManutenzioneOrdinaria()).plusYears(1)));
				car.modificaAuto(a);
			
			}
			if(manutenzioneS.plusDays(2).equals(today)){
				a.setDataManutenzioneStraordinaria(null);
				car.modificaAuto(a);
			}
		}
	}
	
	
	public Object inserisciAuto(ArrayList<String> parameters) {
		if(car.equals(null))
			return false;
		
		Auto a;
		String targa = parameters.get(0);
		String modello = parameters.get(1);
		char fascia = parameters.get(2).charAt(0);
		String dataManutenzioneOrd = parameters.get(4);
		double ultimoKm = Double.parseDouble(parameters.get(5));
		a = new Auto(modello, targa, fascia, "", dataManutenzioneOrd, ultimoKm);
		return car.inserisciAuto(a);
	}
	
	public Object modificaAuto(ArrayList<String> parameters) {
		if(car.equals(null))
			return false;
		
		Auto a;
		String targa = parameters.get(0); // non modificabile!
		String modello = parameters.get(1);
		char fascia = parameters.get(2).charAt(0);
		String dataManutenzioneStraord = parameters.get(3); //gestito gi� da inserisciInManutenzione... non modificabile?
		String dataManutenzioneOrd = parameters.get(4); 
		double ultimoKm = Double.parseDouble(parameters.get(5)); //metterlo non modificabile?
		a = new Auto(modello, targa, fascia, dataManutenzioneStraord, dataManutenzioneOrd, 
				ultimoKm);
		return car.modificaAuto(a);
	}
	
	public Object eliminaAuto(String parameter) {
		if(car.equals(null))
			return false;
		
		return car.rimuoviAuto(parameter);
	}
	
	public Object cercaAuto(ArrayList<String> parameters){
		if(car.equals(null))
			return new ArrayList<Auto>();
		
		 ArrayList<Auto> auto = new  ArrayList<Auto>();
		
		try {
			ContrattoBusiness contratto = new ContrattoBusiness();
			ArrayList<Contratto> contratti = new ArrayList<Contratto>();
			auto = car.autoSistema();
			contratti = contratto.getContrattiAttivi();
				
			LocalDate dataInizio = InputController.getDate(parameters.get(1));
			LocalDate dataFine = InputController.getDate(parameters.get(2));
			
			Iterator<Contratto> it = contratti.iterator();
			while(it.hasNext()){
				Contratto corrente = it.next();
				LocalDate dataInizioContratto = InputController.getDate(corrente.getDataInizio());
				LocalDate dataFineContratto = InputController.getDate(corrente.getDataFine());
				
				if((dataInizio.isAfter(dataInizioContratto) && 
						dataInizio.isBefore(dataFineContratto)) ||
					(dataFine.isAfter(dataInizioContratto) && 
							dataFine.isBefore(dataFineContratto)) ||
					(dataInizio.isBefore(dataInizioContratto) && 
							dataFine.isAfter(dataFineContratto))) {
					
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
			
			if(parameters.get(0) != "Qualsiasi"){
				Iterator<Auto> iter = auto.iterator();
				while(iter.hasNext()){
					Auto current = iter.next();
					if(current.getFascia() != parameters.get(0).charAt(0))
						auto.remove(current);
				}
			}
			return auto;
		}
		catch (InstantiationException | IllegalAccessException e) {
			return new ArrayList<Auto>();
		}
	}
	
	
	public Object inserisciInManutenzione(String parameter) {
		boolean successo;
		
		if(car.equals(null))
			return false;
		
		ArrayList<Auto> autoSistema = car.autoSistema();
		Iterator<Auto> it = autoSistema.iterator();
		while(it.hasNext()) {
			Auto a = it.next();
			if(a.getTarga().equals(parameter)) {
				if( ChronoUnit.DAYS.between
					(InputController.getDate(a.getDataManutenzioneStraordinaria()),
							LocalDate.now()) < 2)
						return false;
				
				String dataAttuale = InputController.getString(LocalDate.now());
				a.setDataManutenzioneStraordinaria(dataAttuale);
				successo = car.modificaAuto(a);
				return successo;
			}
		}
		return false;
	}
	
	
	public Object inserisciNuovoChilometraggio(Auto a) {
		if(car.equals(null))
			return false;
		
		return car.modificaAuto(a);
	}
	
	public Object getDatiAuto(String parameter) throws ObjectNotFoundException {
		if(car.equals(null))
			return null;
		
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
		if(car.equals(null))
			return new ArrayList<Auto>();
		
		return car.autoSistema();
	}
	
	public void chiudiManutenzione() {
		if(!car.equals(null)) {
			ArrayList<Auto> autoSistema = car.autoSistema();
			Iterator<Auto> it = autoSistema.iterator();
			while(it.hasNext()) {
				Auto a = it.next();
				if(ChronoUnit.DAYS.between(LocalDate.now(),
						InputController.getDate(a.getDataManutenzioneStraordinaria())) == 2 )
					a.setDataManutenzioneStraordinaria("");
			}
		}
	}
	
}
