package presentation;

import java.util.ArrayList;

import presentation.command.AccessoDatiOperatore;
import presentation.command.AnnullaContratto;
import presentation.command.CalcolaSaldo;
import presentation.command.CercaAuto;
import presentation.command.ChiusuraContratto;
import presentation.command.Command;
import presentation.command.EliminazioneAuto;
import presentation.command.EliminazioneOperatore;
import presentation.command.GetDatiCliente;
import presentation.command.GetDatiContratto;
import presentation.command.GetPrezziAttuali;
import presentation.command.Initialize;
import presentation.command.InserimentoAuto;
import presentation.command.InserimentoCliente;
import presentation.command.InserimentoManutenzione;
import presentation.command.InserimentoOperatore;
import presentation.command.ModificaContratto;
import presentation.command.ModificaDatiCliente;
import presentation.command.ModificaDatiOperatore;
import presentation.command.ModificaListinoPrezzi;
import presentation.command.NotificaRitiro;
import presentation.command.StipulaContratto;
import utility.LoginUtility;
/**
 * Classe di gestione delle richieste da parte dell'utente implementa FrontControllerI
 * 
 * @author Mattia Menna
 * @author Giuseppe Onesto
 * @see presentation.FrontControllerI
 */

public class ApplicationController implements ApplicationControllerI {
	/**
	 * Oggetto di tipo Command per l'esecuzione delle operazioni
	 */
	Command command;
	/**
	 * Oggetto di tipo ViewDispatcher per lo switch delle interfacce
	 */
	ViewDispatcher dispatcher;
	/**
	 * Oggetto di tipo Object che rappresenta il risultato dell'operazione
	 * eseguita
	 */
	Object ritorno = null;
	
	
	
	
	public Object handleRequest(String request){
		switch(request){
		case "ModificaProfilo":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("ModificaDatiOperatore.fxml");
		break;
		
		case "MenuOperatore":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("menuOperatore.fxml");
		break;
		
		case "Logout":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("Avvio.fxml");
		break;
		
		case "CercaAuto":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("CercaAuto.fxml");
		break;
		
		case "NuovoContratto":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("NuovoContratto.fxml");
		break;
		
		case "ChiudiContratto":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("ChiudiContratto.fxml");
		break;
		
		case "ModificaId":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("ModificaId.fxml");
		break;
		
		case "ModificaContratto":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("ModificaContratto.fxml");
		break;
		
		case "GetDatiContratto":
			command = new GetDatiContratto();
			ritorno = command.Execute(GestioneSessione.getId());
		break;
		
		case "AnnullaContratto":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("AnnullaContratto.fxml");
		break;
		
		case "NotificaRitiro":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("NotificaRitiro.fxml");
		break;
		
		case "CercaCliente":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("CercaCliente.fxml");
		break;
		
		case "Manutenzione":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("Manutenzione.fxml");
		break;
		
		case "Initialize":
			command = new Initialize();
			ritorno = command.Execute("");
		break;
		
		case "NuovoOperatore":
						dispatcher = new ViewDispatcher();
						dispatcher.setInterface("NuovoOperatore.fxml");
		break;
					
		case "MenuAmministratore":
					dispatcher = new ViewDispatcher();
					dispatcher.setInterface("menuAmministratore.fxml");
		break;
		
		case "EliminaOperatore":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("EliminaOperatore.fxml");
		break;
		
		case "NuovaAuto":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("NuovaAuto.fxml");
		break;
		
		case "EliminaAuto":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("EliminaAuto.fxml");
		break;
		case "GetPrezziAttuali":
			command = new GetPrezziAttuali();
			ritorno = command.Execute("");
		break;
		
		case "ModificaPrezzi":
			dispatcher = new ViewDispatcher();
			dispatcher.setInterface("ModificaListino.fxml");
		break;
		}
		
		
		
		return ritorno;
	}
	
	
	public Object handleRequest(String request,ArrayList<String> parameters){
		switch(request){
		
		case "login":
			String result = login(parameters.get(0),parameters.get(1));
			switch(result){
			case "operatore":
				dispatcher = new ViewDispatcher();
				dispatcher.setInterface("menuOperatore.fxml");
			break;
			
			case "amministratore":
				dispatcher = new ViewDispatcher();
				dispatcher.setInterface("menuAmministratore.fxml");
			break;
			case "":
				dispatcher = new ViewDispatcher();
				dispatcher.showMessage(1,"Riprovare","Login Errato!");
			break;
				
			}
		break;
		
		case "ModificaDatiOperatore":
			command = new ModificaDatiOperatore();
			ritorno = command.Execute(parameters);
			
		break;
		
		case "CercaAuto":
			command = new CercaAuto();
			ritorno = command.Execute(parameters);
		break;
		
		case "NuovoContratto":
			command = new StipulaContratto();
			ritorno = command.Execute(parameters);
		break;
		
		case "CalcolaSaldo":
			command = new CalcolaSaldo();
			ritorno = command.Execute(parameters);
		break;
		
		case "ChiudiContratto":
			command = new ChiusuraContratto();
			ritorno = command.Execute(parameters);
		break;
		
		case "ModificaContratto":
			command = new ModificaContratto();
			ritorno = command.Execute(parameters);
		break;
		
		case "AnnullaContratto":
			command = new AnnullaContratto();
			ritorno = command.Execute(parameters.get(0));
		break;
		
		case "NotificaRitiro":
			command = new NotificaRitiro();
			ritorno = command.Execute(parameters.get(0));
		break;
			
		case "InserisciCliente":
			command = new InserimentoCliente();
			ritorno = command.Execute(parameters);
		break;
		
		case "CercaCliente":
			command = new GetDatiCliente();
			ritorno = command.Execute(parameters.get(0));
		break;
		
		case "ModificaDatiCliente":
			command = new ModificaDatiCliente();
			ritorno = command.Execute(parameters);
		break;
		
		case "InserisciManutenzione":
			command = new InserimentoManutenzione();
			ritorno = command.Execute(parameters.get(0));
		break;
			
		case "NuovoOperatore":
			command = new InserimentoOperatore();
			ritorno = command.Execute(parameters);
		break;
		
		case "EliminaOperatore":
			command = new EliminazioneOperatore();
			ritorno = command.Execute(parameters.get(0));
		break;
		
		case "NuovaAuto":
			command = new InserimentoAuto();
			ritorno = command.Execute(parameters);
		break;
		
		case "EliminaAuto":
			command = new EliminazioneAuto();
			ritorno = command.Execute(parameters.get(0));
		break;
		
		case "ModificaListino":
			command = new ModificaListinoPrezzi();
			ritorno = command.Execute(parameters);
		break;
		
		case "AccessoDatiOperatore":
			command = new AccessoDatiOperatore();
			ritorno = command.Execute(parameters.get(0));
		break;
		}
		return ritorno;
	}
	/**
	 * Metodo di login, utilizza le funzioni di LoginUtility
	 * @param username Username da validare
	 * @param password Password associata all'username
	 * @return Esito del login
	 */
	private String login(String username, String password){
		LoginUtility l = new LoginUtility();
		if(l.correctPassword("operatore" + username, password)) {
			GestioneSessione.setUsername(username);
			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add(username);
			
			ArrayList<String> datiOperatore = (ArrayList<String>)
					handleRequest("AccessoDatiOperatore",parameters);
			
			if(datiOperatore.isEmpty() ||
					(datiOperatore.get(0).equals("") && datiOperatore.get(1).equals("") && 
					datiOperatore.get(2).equals("") && datiOperatore.get(3).equals(""))) {
				ViewDispatcher v = new ViewDispatcher();
				v.showMessage(2, "Attenzione", "Impossibile caricare i dati personali. \n"
				+ "Il login � stato comunque effettuato correttamente con il nickname: " + username);			
				}
			else {
				GestioneSessione.setNomeOperatore(datiOperatore.get(0));
				GestioneSessione.setCognomeOperatore(datiOperatore.get(1));
				GestioneSessione.setIndirizzoOperatore(datiOperatore.get(2));
				GestioneSessione.setTelefonoOperatore(datiOperatore.get(3));
			}
			
			return "operatore";
		}
		
		else if(l.correctPassword(username, password))
			return "amministratore";
		else
			return "";		
	}

}
