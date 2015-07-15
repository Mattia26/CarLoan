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
/**
 * Classe di controllo per l'interfaccia menuOperatore
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class MenuOperatoreController implements Initializable{

	@FXML
	private Label NomeOperatoreR;
	
	@FXML
	private Label CognomeOperatoreR;
	
	@FXML
	private Label TelefonoOperatoreR;
	
	@FXML
	private Label indirizzo;
	
	
	private FrontController fc = new FrontController();
		
	
	
	/**
	 * Gestore per le operazioni eseguite alla pressione della label Modifica Profilo
	 */
	@FXML
	public void ModificaProfilo(){
		fc.handleRequest("ModificaProfilo");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione della label Logout
	 */
	@FXML
	public void Logout(){
		GestioneSessione.azzera();
		fc.handleRequest("Logout");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Nuovo Contratto
	 */
	@FXML
		public void CercaAuto(){
			fc.handleRequest("CercaAuto");
		}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Chiudi Contratto
	 */
	@FXML
		public void ChiudiContratto(){
		fc.handleRequest("ChiudiContratto");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Modifica Contratto
	 */
	@FXML
		public void ModificaContratto(){
		fc.handleRequest("ModificaId");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Annulla Contratto
	 */
	@FXML
		public void annullaContratto(){
		fc.handleRequest("AnnullaContratto");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Notifica Ritiro
	 */
	@FXML
		public void notificaRitiro(){
		fc.handleRequest("NotificaRitiro");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Cerca Cliente
	 */
	@FXML
		public void cercaCliente(){
		fc.handleRequest("CercaCliente");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Manutenzione
	 */
	@FXML
		public void manutenzione(){
		fc.handleRequest("Manutenzione");
	}
	/**
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeOperatoreR.setText(GestioneSessione.getNomeOperatore());
		CognomeOperatoreR.setText(GestioneSessione.getCognomeOperatore());
		TelefonoOperatoreR.setText(GestioneSessione.getTelefonoOperatore());
		indirizzo.setText(GestioneSessione.getIndirizzoOperatore());
		String messaggi;
		
		try {
		messaggi = (String)fc.handleRequest("Initialize");
		}
		catch (NullPointerException e) {
			messaggi = "";
		}
		
		if(messaggi != ""){
			ViewDispatcher v = new ViewDispatcher(true);
			v.showMessage(0, "Informazione di servizio", messaggi);
		}
		
	}
	
	
	
}
