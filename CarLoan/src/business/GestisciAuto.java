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
	private ContrattoBusiness contratto;
	
	public Object cercaAuto(ArrayList<String> parameters){
		 ArrayList<Auto> auto = new  ArrayList<Auto>();
		 ArrayList<Contratto> contratti = new ArrayList<Contratto>();
		car = new AutoBusiness();
		contratto = new ContrattoBusiness();
		
		auto = car.autoSistema();
		contratti = contratto.getContrattiAttivi();
		
		
		
			
			LocalDate dataInizio = InputController.getCalendar(parameters.get(1));
			LocalDate dataFine = InputController.getCalendar(parameters.get(2));
			
			Iterator<Contratto> it = contratti.iterator();
			
			while(it.hasNext()){
				Contratto corrente = it.next();
				LocalDate dataInizioContratto = InputController.getCalendarDB(corrente.getDataInizio());
				LocalDate dataFineContratto = InputController.getCalendarDB(corrente.getDataFine());
				
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
	

}
