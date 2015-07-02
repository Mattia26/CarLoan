package presentation.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.GestioneSessione;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuOperatoreController implements Initializable{

	@FXML
	private Label NomeOperatoreR;
	
	@FXML
	private Label CognomeOperatoreR;
	
	@FXML
	private Label TelefonoOperatoreR;
	
	
	
	
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
	
	@FXML
		public void ChiudiContratto(){
		FrontController fc = new FrontController();
		fc.handleRequest("ChiudiContratto");
	}
	
	@FXML
		public void ModificaContratto(){
		FrontController fc = new FrontController();
		fc.handleRequest("ModificaId");
	}
	
	@FXML
		public void annullaContratto(){

		FrontController fc = new FrontController();
		fc.handleRequest("AnnullaContratto");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeOperatoreR.setText(GestioneSessione.getNomeOperatore());
		CognomeOperatoreR.setText(GestioneSessione.getCognomeOperatore());
		TelefonoOperatoreR.setText(GestioneSessione.getTelefonoOperatore());
		
	}
	
	
	
}
