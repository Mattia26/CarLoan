package business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import utility.InputController;
import business.entity.AutoBusiness;
import business.entity.ContrattoBusiness;
import business.entity.DatabaseInstantiationException;
import entity.Auto;
import entity.Contratto;

public class GestisciAuto {
	/**
	 * Attributo privato di tipo AutoBusiness
	 */
	private AutoBusiness car;
	
	/**
	 * Costruttore: prova ad istanziare l'attributo car; altrimenti lo setta a null
	 */
	public GestisciAuto() {
			try {
				car = new AutoBusiness();
			} catch (DatabaseInstantiationException e) {
				// TODO Auto-generated catch block
				car = null;
			}	
	}
	
	/**
	 * Metodo che verrà richiamato al Login di un Operatore.
	 * In particolare, se car è diverso da null, toglie dalla manutenzione ordinaria
	 * tutte le auto la cui data di inizio manutenzione ordinaria era due giorni prima di oggi
	 * e fà lo stesso per le auto in manutenzione straordinaria da due giorni.
	 */
	public void Initialize(){
		
		if(car==null)
			return;
		
		ArrayList<Auto> auto = new ArrayList<Auto>();
		LocalDate today = LocalDate.now();
		auto = car.autoSistema();
		Iterator<Auto> it = auto.iterator();
		
		while(it.hasNext()){
			Auto a = it.next();
			
			LocalDate manutenzioneO =  InputController.getDate(a.getDataManutenzioneOrdinaria());
			if( manutenzioneO.plusDays(2).equals(today)){
				a.setDataManutenzioneOrdinaria(InputController.getString
					(InputController.getDate(a.getDataManutenzioneOrdinaria()).plusYears(1)));
					car.modificaAuto(a);
			}
			
			try {
				LocalDate manutenzioneS = InputController.getDate
					(a.getDataManutenzioneStraordinaria());
			
				if(manutenzioneS.plusDays(2).equals(today)){
					a.setDataManutenzioneStraordinaria(null);
					car.modificaAuto(a);
				}
			}
			catch(NullPointerException e) {
				
			}
		}
		
	}
	
	
	/**
	 * Metodo per l'inserimento di un auto
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * l'inserimento dell'auto
	 * @return true se l'auto è stata inserita correttamente; false altrimenti
	 */
	public Object inserisciAuto(ArrayList<String> parameters) {
		
		if(car==null)
			return false;
		
		Auto a;
		String targa = parameters.get(0);
		String modello = parameters.get(1);
		char fascia = parameters.get(2).charAt(0);
		String dataManutenzioneOrd = parameters.get(3);
		double ultimoKm = Double.parseDouble(parameters.get(4));
		
		a = new Auto(modello, targa, fascia, "", dataManutenzioneOrd, ultimoKm);
		return car.inserisciAuto(a);
		
	}
	
	/**
	 * Metodo per la modifica di un auto
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * la modifica dell'auto
	 * @return true se l'auto è stata modificata correttamente; false altrimenti
	 */
	public Object modificaAuto(ArrayList<String> parameters) {
		
		if(car==null)
			return false;
		
			Auto a;
			String targa = parameters.get(0); // non modificabile!
			String modello = parameters.get(1);
			char fascia = parameters.get(2).charAt(0);
			String dataManutenzioneStraord = parameters.get(3); //gestito giï¿½ da inserisciInManutenzione... non modificabile?
			String dataManutenzioneOrd = parameters.get(4); 
			double ultimoKm = Double.parseDouble(parameters.get(5)); //metterlo non modificabile?
			
			a = new Auto(modello, targa, fascia, dataManutenzioneStraord, 
					dataManutenzioneOrd, ultimoKm);
		return car.modificaAuto(a);	
		
	}
	
	/**
	 * Metodo per la rimozione di un auto
	 * @param parameters: String contenente la targa per l'eliminazione dell'auto
	 * @return true se l'auto è stata rimossa correttamente; false altrimenti
	 */
	public Object eliminaAuto(String parameter) {

		if(car==null)
			return false;
		try {
		ContrattoBusiness contratto = new ContrattoBusiness();
		ArrayList<Contratto> contratti = new ArrayList<Contratto>();
		contratti = contratto.getContrattiAttivi();
		Iterator<Contratto> it = contratti.iterator();
			
		while(it.hasNext()){
			Contratto current = it.next();
			if(current.getTargaMacchina().equals(parameter))
				return false;
		}
		
		return car.rimuoviAuto(parameter);
		
		}
		catch (DatabaseInstantiationException e) {
			// TODO Auto-generated catch block
			return false;
		}	
	}
	
	/**
	 * Metodo per la ricerca di un auto disponibile
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * la ricerca dell'auto: quali data di inizio, data di fine, fascia di appartenenza.
	 * @return ArrayList<Auto>: insieme auto disponibili in base ai parametri di input.
	 */
	public Object cercaAuto(ArrayList<String> parameters){
		
		if(car==null)
			return new ArrayList<Auto>();
		
		ArrayList<Auto> auto;
		try {
			ContrattoBusiness contratto = new ContrattoBusiness();
			ArrayList<Contratto> contratti = new ArrayList<Contratto>();
			auto = car.autoSistema();
			contratti = contratto.getContrattiAttivi();
			LocalDate dataInizio = InputController.getDate(parameters.get(1));
			LocalDate dataFine = InputController.getDate(parameters.get(2));
			Iterator<Contratto> it = contratti.iterator();
			Iterator<Auto> itr = auto.iterator();
			
			while(it.hasNext()){
				Contratto corrente = it.next();
				LocalDate dataInizioContratto = InputController.getDate(corrente.getDataInizio());
				LocalDate dataFineContratto = InputController.getDate(corrente.getDataFine());
				
				if((dataInizio.isAfter(dataInizioContratto) && 
						dataInizio.isBefore(dataFineContratto)) ||
					(dataFine.isAfter(dataInizioContratto) && 
							dataFine.isBefore(dataFineContratto)) ||
					(dataInizio.isBefore(dataInizioContratto) && 
							dataFine.isAfter(dataFineContratto)) ) {
					
					
					while(itr.hasNext()){
						Auto corr = itr.next();
						if(corr.getTarga().equals(corrente.getTargaMacchina())){
							auto.remove(corr);
							break;
						}
					}		
				}
			}
			
			while(itr.hasNext()) {
				Auto corr = itr.next();
				try {
				LocalDate dataManOrd = InputController.getDate
						(corr.getDataManutenzioneOrdinaria());
				LocalDate dataManStr = InputController.getDate
						(corr.getDataManutenzioneStraordinaria());
				if(dataInizio.isBefore(dataManOrd) && dataFine.isAfter(dataManOrd))
					auto.remove(corr);
				else if(dataInizio.isBefore(dataManStr) && dataFine.isAfter(dataManStr))
					auto.remove(corr);
				}
				catch(NullPointerException e) {
					
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
		catch (DatabaseInstantiationException e) {
			return new ArrayList<Auto>();
		}
	}
	
	/**
	 * Metodo per l'inserimento in manutenzione straordinaria di un'auto.
	 * Chiaramente non è possibile inserire un'auto in manutenzione straordinaria se essa
	 * è attualmente in noleggio. Pertanto il metodo controllerà che l'auto non sia attualmente
	 * presente nei contratti attivi, quindi si potrà procedere all'inserimento in manutenzione.
	 * @param parameter: String contenente la targa dell'auto da inserire in manutenzione
	 * @return true se l'auto è stata inserita in manutenzione correttamente; false altrimenti
	 */
	public Object inserisciInManutenzione(String parameter) {
		
		if(car==null)
			return false;
		
		
		try {
			ContrattoBusiness cb= new ContrattoBusiness();
			ArrayList<Contratto> contrattiAttivi = cb.getContrattiAttivi();
			Iterator<Contratto> it = contrattiAttivi.iterator();
			
			while(it.hasNext()) {
				Contratto c = it.next();
				if(c.getTargaMacchina().equals(parameter))
					return false;
			}
			Auto a = (Auto) getDatiAuto(parameter);
			if(!a.getDataManutenzioneStraordinaria().equals(""))
				return false;
				
			String dataAttuale = InputController.getString(LocalDate.now());
			a.setDataManutenzioneStraordinaria(dataAttuale);
			return car.modificaAuto(a);
			
		}
		catch(ObjectNotFoundException | DatabaseInstantiationException e) {
			return false;
		}
		
	}
		
	
	/**
	 * Metodo per l'inserimento di un nuovo ultimo chilometraggio per l'auto in input
	 * @param a di tipo Auto: auto da modificare inserendo il nuovo ultimo chilometraggio
	 * @param ultimoKm di tipo double: valore nuovo ultimo chilometraggio.
	 * @return true se l'auto è stata modificata correttamente inserendo il nuovo
	 * ultimo chilometraggio; false altrimenti
	 */
	public Object inserisciNuovoChilometraggio(Auto a, double ultimoKm) {
		
		if(car==null)
			return false;
		
		a.setUltimoChilometraggio(ultimoKm);
		return car.modificaAuto(a);	
	}
	
	/**
	 * Metodo che, a partire dalla stringa in input indicante la targa, restituisce i dati
	 * dell'auto associata a quella targa.
	 * @param parameter di tipo String: indica la targa dell'auto.
	 * @return Auto avente targa equivalente alla stringa in input.
	 * @throws ObjectNotFoundException
	 */
	public Object getDatiAuto(String parameter) throws ObjectNotFoundException {
		
		if(car==null)
			return null;
		
		ArrayList<Auto> autoSistema = car.autoSistema();
		Iterator<Auto> it = autoSistema.iterator();
		while(it.hasNext()) {
			Auto a = it.next();
			if(a.getTarga().equals(parameter)) 
				return a;
			}
		
			throw new ObjectNotFoundException();
	}
	
	/**
	 * Prende tutte le auto registrate nel sistema.
	 * @return ArrayList<Auto> contenente tutte le auto presenti nel sistema.
	 */
	public Object getAutoSistema() {
		
		if(car==null)
			return new ArrayList<Auto>();
		
			return car.autoSistema();
	}
	
}
