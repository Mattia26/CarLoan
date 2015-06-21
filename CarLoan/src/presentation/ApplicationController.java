package presentation;

import java.util.ArrayList;

import presentation.command.Command;
import presentation.command.ModificaDatiOperatore;

public class ApplicationController implements ApplicationControllerI {
	Command command;
	ViewDispatcher dispatcher;
	Object ritorno;
	
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
		
		
		}
		
		
		
		return null;
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
				handleRequest("MenuOperatore");
			}
			else{
				dispatcher = new ViewDispatcher();
				dispatcher.showMessage(1, "Errore", "Non è stato possibile portare a termine la modifica");
			}
		break;
		}
		return null;
	}
	
	private String login(String username, String password){
		GestioneSessione.setNomeOperatore("Mario");
		GestioneSessione.setCognomeOperatore("Rossi");
		GestioneSessione.setTelefonoOperatore("3333333333");
		return "operatore";
	}

}
