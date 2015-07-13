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
import presentation.command.Initialize;
import presentation.command.InserimentoAuto;
import presentation.command.InserimentoCliente;
import presentation.command.InserimentoManutenzione;
import presentation.command.InserimentoOperatore;
import presentation.command.InserisciListinoPrezzi;
import presentation.command.ModificaContratto;
import presentation.command.ModificaDatiCliente;
import presentation.command.ModificaDatiOperatore;
import presentation.command.NotificaRitiro;
import presentation.command.StipulaContratto;
import utility.LoginUtility;

public class ApplicationController implements ApplicationControllerI {
	Command command;
	ViewDispatcher dispatcher;
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
			case "errore":
				dispatcher = new ViewDispatcher();
				dispatcher.showMessage(1,"Riprovare","Login Errato!");
			break;
				
			}
		break;
		
		case "ModificaDatiOperatore":
			command = new ModificaDatiOperatore();
			if((boolean)command.Execute(parameters)){
				dispatcher = new ViewDispatcher();
				dispatcher.showMessage(0, "Informazione", "Operazione avvenuta con successo");
				handleRequest("MenuOperatore");
			}
			else{
				dispatcher = new ViewDispatcher();
				dispatcher.showMessage(1, "Errore", "Non è stato possibile portare a termine la modifica");
			}
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
			ritorno = command.Execute(parameters.get(0));
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
			command = new InserisciListinoPrezzi();
			ritorno = command.Execute(parameters);
		break;
		
		case "AccessoDatiOperatore":
			command = new AccessoDatiOperatore();
			ritorno = command.Execute(parameters.get(0));
		break;
		}
		return ritorno;
	}
	
	private String login(String username, String password){
		LoginUtility l = new LoginUtility();
		if(l.correctPassword("operatore" + username, password)) {
			ArrayList<String> parameter = new ArrayList<String>();
			parameter.add(username);
			
			ArrayList<String> op = (ArrayList<String>)handleRequest("AccessoDatiOperatore",parameter);
			GestioneSessione.setNomeOperatore(op.get(0));
			GestioneSessione.setCognomeOperatore(op.get(1));
			GestioneSessione.setIndirizzoOperatore(op.get(2));
			GestioneSessione.setTelefonoOperatore(op.get(3));
			return "operatore";
			
			
		}
		else if(l.correctPassword(username, password))
			return "amministratore";
		else
			return "";		
	}

}
