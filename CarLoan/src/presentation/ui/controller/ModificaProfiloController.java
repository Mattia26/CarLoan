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
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia ModificaDatiOperatore
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class ModificaProfiloController implements Initializable{

	@FXML
	private TextField NomeField;
	
	@FXML
	private TextField CognomeField;
	
	@FXML
	private TextField TelefonoField;
	
	@FXML
	private TextField indirizzo;
	
	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Modifica
	 */
	@FXML
	public void Modifica(){
		ArrayList<String> parameters = new ArrayList<String>();
		FrontController fc = new FrontController();
		ViewDispatcher v = new ViewDispatcher();
		if(NomeField.getText().equals(GestioneSessione.getNomeOperatore()) &&
			CognomeField.getText().equals(GestioneSessione.getCognomeOperatore()) &&
			TelefonoField.getText().equals(GestioneSessione.getTelefonoOperatore()) &&
			indirizzo.getText().equals(GestioneSessione.getIndirizzoOperatore()) ) 
			v.showMessage(2, "Informazione", 
					"I dati inseriti sono gli stessi già presenti nel sistema");
		else {
			parameters.add(NomeField.getText());
			parameters.add(CognomeField.getText());
			parameters.add(TelefonoField.getText());
			parameters.add(indirizzo.getText());
			if( (boolean)fc.handleRequest("ModificaDatiOperatore",parameters))
				v.showMessage(0, "Informazione", "Modifica dati avvenuta con successo");
			else
				v.showMessage(1, "Errore", "Purtroppo la modifica dei dati non è riuscita");
			
		}	
			
	}
	/**
	 * Gestore delle operazioni alla pressione del tassto Indietro
	 */
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
    /**
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeField.setText(GestioneSessione.getNomeOperatore());
		CognomeField.setText(GestioneSessione.getCognomeOperatore());
		TelefonoField.setText(GestioneSessione.getTelefonoOperatore());
		indirizzo.setText(GestioneSessione.getIndirizzoOperatore());
		
	}

}
