package presentation.ui.controller;

import presentation.FrontController;
import presentation.GestioneSessione;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuOperatoreController {

	@FXML
	private Label NomeOperatoreR;
	
	@FXML
	private Label CognomeOperatoreR;
	
	@FXML
	private Label TelefonoOperatoreR;
	
	
	@FXML
	public void show(Event e){
		NomeOperatoreR.setText(GestioneSessione.getNomeOperatore());
		CognomeOperatoreR.setText(GestioneSessione.getCognomeOperatore());
		TelefonoOperatoreR.setText(GestioneSessione.getTelefonoOperatore());
	}
	
	@FXML
	public void ModificaProfilo(Event e){
		FrontController fc = new FrontController();
		fc.handleRequest("ModificaProfilo");
	}
	
	@FXML
	public void Logout(Event e){
		GestioneSessione.azzera();
		FrontController fc = new FrontController();
		fc.handleRequest("Logout");
	}

	@FXML
		public void CercaAuto(Event e){
			FrontController fc = new FrontController();
			fc.handleRequest("CercaAuto");
		}
	
	
}
