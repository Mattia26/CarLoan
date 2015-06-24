package presentation;

import java.util.ArrayList;

import presentation.command.CalcolaSaldo;
import presentation.command.CercaAuto;
import presentation.command.ChiusuraContratto;
import presentation.command.Command;
import presentation.command.GetDatiContratto;
import presentation.command.ModificaContratto;
import presentation.command.ModificaDatiOperatore;
import presentation.command.StipulaContratto;

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
			ritorno = command.Execute(parameters);
		break;
		
		case "ModificaContratto":
			command = new ModificaContratto();
			ritorno = command.Execute(parameters);
		break;
		
		}
		return ritorno;
	}
	
	private String login(String username, String password){
		GestioneSessione.setNomeOperatore("Mario");
		GestioneSessione.setCognomeOperatore("Rossi");
		GestioneSessione.setTelefonoOperatore("3333333333");
		return "operatore";
	}

}
