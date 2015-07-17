package business;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.temporal.ChronoUnit;

import presentation.GestioneSessione;
import utility.InputController;
import entity.Auto;
import entity.Cliente;
import entity.Contratto;
import entity.Ditta;
import entity.ListinoPrezzi;
import business.entity.AutoBusiness;
import business.entity.ClienteBusiness;
import business.entity.ContrattoBusiness;
import business.entity.DatabaseInstantiationException;
import business.entity.DittaBusiness;
import business.entity.ListinoBusiness;

/**
 * Classe che permette la gestione di tutte le operazioni possibili sui contratti, a livello
 * di business.
 * @author Giuseppe Onesto
 * @author Mattia Menna
 *
 */
public class GestisciContratto {
	
	/**
	 * Attributo privato di tipo AutoBusiness
	 */
	private ContrattoBusiness cb;
	
	/**
	 * Costruttore: prova ad istanziare l'attributo cb; altrimenti lo setta a null
	 */
	public GestisciContratto() {
		try {
			cb = new ContrattoBusiness();
		} catch (DatabaseInstantiationException e) {
			// TODO Auto-generated catch block
			cb = null;
		}
	}
	
	/**
	 * Metodo che verrà richiamato al Login di un Operatore.
	 * In particolare, se cb è diverso da null: chiude tutti i contratti la cui data di inizio
	 * corrisponde alla data precedente alla data attuale, inoltre restituisce tutti i contratti
	 *  la cui fine era prevista nel giorno antecedente a quello attuale, in modo da avvisare 
	 *  l'operatore che procederà con le procedure del caso.
	 */
	public Object Initialize(String s){
		if(cb==null)
			return new ArrayList<Contratto>();
		
		LocalDate yesterday = LocalDate.now().minusDays(1);
		ArrayList<Contratto> ritorno = new ArrayList<Contratto>();
		ArrayList<Contratto> contratti = cb.getContrattiSistema();
		Iterator<Contratto> it = contratti.iterator();	
		
		while(it.hasNext()) {
			Contratto current = it.next();
			LocalDate dataInizio = InputController.getDate(current.getDataInizio());
			LocalDate dataFine = InputController.getDate(current.getDataFine());
			if(dataInizio.isBefore(LocalDate.now()) && !current.macchinaRitirata()) {
				current.setDataFine(InputController.getString(LocalDate.now()));
				current.setChiuso(true);
				cb.modificaContratto(current);
			}
			else if(dataFine.equals(yesterday) && !current.chiuso()) 
					ritorno.add(current);
			
		}
			
		GestisciAuto g = new GestisciAuto();
		g.Initialize();
		
		
		return ritorno;
	}
	
	/**
	 * Metodo per la stipula nuovo contratto
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * stipulare il contratto
	 * @return true se il contratto è stato stipulato correttamente; false altrimenti
	 */
	public Object nuovoContratto(ArrayList<String> parameters) {
		
		if(cb==null)
			return false;
		
		String cfCliente = parameters.get(6);
		String targaMacchina = parameters.get(0);
		String dataInizio = parameters.get(1);
		String dataFine = parameters.get(2);
		int acconto = Integer.parseInt(parameters.get(7));
		char tipo = parameters.get(4).charAt(0);
		char tipoKm = parameters.get(5).charAt(0);
		String dittaRestituzione = parameters.get(3);
		boolean autoRitirata;
	
		if(InputController.getDate(dataInizio).equals(LocalDate.now()))
			autoRitirata=true;
		else
			autoRitirata=false;	
		
		Contratto c = new Contratto(0, cfCliente, targaMacchina, dataInizio, dataFine, 
				acconto, tipo, tipoKm, dittaRestituzione, false, autoRitirata);
		return cb.inserisciContratto(c);
		
	}
	
	
	/**
	 * Metodo per la modifica dei dati di un contratto
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * la modifica dei dati del contratto
	 * @return true se il contratto è stato modificato correttamente; false altrimenti
	 */
	public Object modificaContratto(ArrayList<String> parameters) {
		
		if(cb==null)
			return false;
		
		String dataInizio = parameters.get(2);
		
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
	
	/**
	 * Metodo per l'annullamento(e quindi rimozione) di un contratto
	 * @param id di tipo String: indica l'id del contratto da annullare
	 * @return true se il contratto è stato annullato correttamente; false altrimenti
	 */
	public Object annullaContratto(String id) {
		
		if(cb==null)
			return false;
		
		try {
			getDatiContratto(id);
			int idC = Integer.parseInt(id);
			return cb.cancellaContratto(idC);
		} 
		catch (ObjectNotFoundException e) {
		// contratto con id non presente nel db tra i contratti attivi
			return false;
		}
		
	}
	
	
	/**
	 * Metodo per la chiusura di un contratto avente id uguale alla String in input.
	 * @param id di tipo String: indica l'id del contratto da annullare
	 * @return true se il contratto è stato annullato correttamente; false altrimenti
	 */
	public Object chiudiContratto(ArrayList<String> parameters) {	
		
		if(cb==null)
			return false;
		
		try {
			Contratto c = (Contratto) getDatiContratto(parameters.get(0));
			GestisciAuto g = new GestisciAuto();
			Auto a = (Auto) g.getDatiAuto(c.getTargaMacchina());
			
			if((boolean)g.inserisciNuovoChilometraggio(a, Integer.parseInt(parameters.get(1)))) {
				c.setDataFine(InputController.getString(LocalDate.now()));
				c.setChiuso(true);
				return cb.modificaContratto(c);
			}
			else
				return false;
		} 
		catch (ObjectNotFoundException e) {
			return false;
		}
	
	}
	
	
	/**
	 * Metodo per segnalare al sistema l'avvenuto ritiro dell'auto.
	 * @param id di tipo String: indica l'id del contratto, la cui relativa auto è
	 * stata correttamente ritirata
	 * @return true se l'impostazione dell'avvenuto ritiro è avvenuta correttamente; 
	 * false altrimenti
	 */
	public Object notificaRitiroAuto(String id) {
		
		if(cb==null)
			return false;
		
		Contratto c;
		try {
			c = (Contratto) getDatiContratto(id);
			if(InputController.getDate(c.getDataInizio()).isAfter(LocalDate.now()))
				return false;
			if(c.macchinaRitirata())
				return false;
			
			c.setRitirata(true);
			return cb.modificaContratto(c);
		} 
		catch (ObjectNotFoundException e) {
			return false;
		}
		
	}
	
	
	/**
	 * Metodo che, a partire dalla stringa in input indicante l'id, restituisce i dati
	 * del contratto associato a quell'id.
	 * @param id di tipo String: indica l'id del contratto.
	 * @return Contratto: avente id uguale alla String in input; null altrimenti
	 * @throws ObjectNotFoundException
	 */
	public Object getDatiContratto(String id) throws ObjectNotFoundException {
		if(cb==null)
			return null;
		
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
	
	/**
	 * Metodo per calcolare il saldo in base a: tipo contratto, tipo chilometraggio, chilometri
	 * percorsi, durata noleggio e fascia dell'auto.
	 * @param parameters: parametri utili per il calcolo: saranno id del contratto e nuovo
	 * ultimo chilometraggio dell'auto.
	 * @return double: il saldo da pagare.
	 */
	public Object calcolaImporto(ArrayList<String> parameters) {
		double importo=-1.0;
		if(cb==null)
			return importo;
		
		try {
			ListinoBusiness lb;	
			int costoTipo;
			int costoTipoKm;
			int kmPercorsi;
			int durataContratto;
			
			String id=parameters.get(0);
			int nuovoKm=Integer.parseInt(parameters.get(1));
			Contratto c;
			
			c = (Contratto) getDatiContratto(id);
			if(InputController.getDate(c.getDataInizio()).isAfter(LocalDate.now()))
				return -2.0;
			
			Character tipo = c.getTipologia();
			Character tipoKm = c.getTipoChilometraggio();
			GestisciAuto g = new GestisciAuto();
			Auto a = (Auto) g.getDatiAuto(c.getTargaMacchina());
			Character fascia = a.getFascia();
			kmPercorsi = nuovoKm - a.getUltimoChilometraggio();
			if(kmPercorsi<0)
				return -3.0;
			
			lb = new ListinoBusiness();
		
			if(tipo.equals('G') && tipoKm.equals('L')) {
				if(GestioneSessione.getCostoGiornaliero()==null)
					costoTipo = lb.getPrezzi("").getCostoGiornaliero();
				else
					costoTipo=Integer.parseInt(GestioneSessione.getCostoGiornaliero());
				if(GestioneSessione.getCostoKmLimitato()==null)
					costoTipoKm = lb.getPrezzi("").getCostoKmLimitato();
				else
					costoTipoKm=Integer.parseInt(GestioneSessione.getCostoKmLimitato());
				
				durataContratto = (int) ChronoUnit.DAYS.between(
					InputController.getDate(c.getDataInizio()), LocalDate.now() );
				
				
				importo = costoTipo * durataContratto + 
					costoTipoKm * (Integer.divideUnsigned(kmPercorsi,50)) - c.getQuotaAcconto(); 
			}
			
			else if(tipo.equals('G') && tipoKm.equals('I')) {
				if(GestioneSessione.getCostoGiornaliero()==null)
					costoTipo = lb.getPrezzi("").getCostoGiornaliero();
				else
					costoTipo=Integer.parseInt(GestioneSessione.getCostoGiornaliero());
				if(GestioneSessione.getCostoKmIllimitato()==null)
					costoTipoKm = lb.getPrezzi("").getCostoKmIllimitato();
				else
					costoTipoKm=Integer.parseInt(GestioneSessione.getCostoKmIllimitato());
				
				durataContratto = (int) ChronoUnit.DAYS.between(LocalDate.now(),
					InputController.getDate(c.getDataInizio()));
			
				importo = costoTipo*durataContratto + costoTipoKm*kmPercorsi 
							- c.getQuotaAcconto();
			}
				
			else if(tipo.equals('S') && tipoKm.equals('L')) {
				if(GestioneSessione.getCostoSettimanale()==null)
					costoTipo = lb.getPrezzi("").getCostoSettimanale();
				else
					costoTipo=Integer.parseInt(GestioneSessione.getCostoSettimanale());
				if(GestioneSessione.getCostoKmLimitato()==null)
					costoTipoKm = lb.getPrezzi("").getCostoKmLimitato();
				else
					costoTipoKm=Integer.parseInt(GestioneSessione.getCostoKmLimitato());
				
				durataContratto = (int) ChronoUnit.WEEKS.between(LocalDate.now(),
					InputController.getDate(c.getDataInizio()));
			
				importo = costoTipo*durataContratto + 
					costoTipoKm * (Integer.divideUnsigned(kmPercorsi,50)) - c.getQuotaAcconto();	
			}
			
			else {
				if(GestioneSessione.getCostoSettimanale()==null)
					costoTipo = lb.getPrezzi("").getCostoSettimanale();
				else
					costoTipo=Integer.parseInt(GestioneSessione.getCostoSettimanale());
				if(GestioneSessione.getCostoKmIllimitato()==null)
					costoTipoKm = lb.getPrezzi("").getCostoKmIllimitato();
				else
					costoTipoKm=Integer.parseInt(GestioneSessione.getCostoKmIllimitato());
				
				durataContratto = (int) ChronoUnit.WEEKS.between(LocalDate.now(),
						InputController.getDate(c.getDataInizio()));
				
				importo = costoTipo*durataContratto + costoTipoKm*kmPercorsi 
							- c.getQuotaAcconto();
			}
			
			if(fascia.equals('a') || fascia.equals('A'))
				return importo*1.2;
			else if(fascia.equals('b') || fascia.equals('B'))
				return importo;
			else
				return importo*0.8;
		} 
		
		
		catch (ObjectNotFoundException e) {
			return importo;
		}
		
	}
	
	/**
	 * Metodo che restituisce l'insieme delle ditte disponibili in cui poter rilasciare l'auto
	 * a fine contratto.
	 * @param s: ditta attuale
	 * @return insieme delle ditte in cui rilasciare l'auto.
	 */
	public Object getDitte(String s) {
		System.out.println("dasjd");
		if(cb==null) 
			return new ArrayList<String>();
		
		DittaBusiness d;
	
		try {
			d = new DittaBusiness();
			ArrayList<String> città=new ArrayList<String>();
			ArrayList<Ditta> ditte = new ArrayList<Ditta>();
			
			ditte = d.getDitte();
			Iterator<Ditta> it = ditte.iterator();
			
			while(it.hasNext()) {
				Ditta curr = it.next();
				città.add(curr.getCittà());	
			}
			
			return città;
		} catch (DatabaseInstantiationException | NullPointerException e) {
			// TODO Auto-generated catch block
			return new ArrayList<String>();
		}
		
	}
	
}
