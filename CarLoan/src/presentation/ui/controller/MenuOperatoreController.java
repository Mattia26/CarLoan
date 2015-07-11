package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
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
	
	private FrontController fc = new FrontController();
		
	
	
	
	@FXML
	public void ModificaProfilo(Event e){
		fc.handleRequest("ModificaProfilo");
	}
	
	@FXML
	public void Logout(Event e){
		GestioneSessione.azzera();
		fc.handleRequest("Logout");
	}

	@FXML
		public void CercaAuto(Event e){
			fc.handleRequest("CercaAuto");
		}
	
	@FXML
		public void ChiudiContratto(){
		fc.handleRequest("ChiudiContratto");
	}
	
	@FXML
		public void ModificaContratto(){
		fc.handleRequest("ModificaId");
	}
	
	@FXML
		public void annullaContratto(){
		fc.handleRequest("AnnullaContratto");
	}
	
	@FXML
		public void notificaRitiro(){
		fc.handleRequest("NotificaRitiro");
	}
	
	@FXML
		public void cercaCliente(){
		fc.handleRequest("CercaCliente");
	}
	
	@FXML
		public void manutenzione(){
		fc.handleRequest("Manutenzione");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeOperatoreR.setText(GestioneSessione.getNomeOperatore());
		CognomeOperatoreR.setText(GestioneSessione.getCognomeOperatore());
		TelefonoOperatoreR.setText(GestioneSessione.getTelefonoOperatore());
		
		String messaggi = (String)fc.handleRequest("Initialize");
		
		if(messaggi != ""){
			ViewDispatcher v = new ViewDispatcher();
			v.showMessage(0, "Informazione di servizio", messaggi);
		}
		
	}
	
	
	
}
