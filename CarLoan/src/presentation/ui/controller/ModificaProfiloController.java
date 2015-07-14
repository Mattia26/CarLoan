package presentation.ui.controller;



import java.util.ArrayList;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ModificaProfiloController {

	@FXML
	private TextField NomeField;
	
	@FXML
	private TextField CognomeField;
	
	@FXML
	private TextField TelefonoField;
	
	private TextField IndirizzoField;
	
	@FXML
	public void setFields(Event e){
		NomeField.setText(GestioneSessione.getNomeOperatore());
		CognomeField.setText(GestioneSessione.getCognomeOperatore());
		TelefonoField.setText(GestioneSessione.getTelefonoOperatore());
		IndirizzoField.setText(GestioneSessione.getIndirizzoOperatore());
	}
	
	@FXML
	public void Modifica(Event e){
		ArrayList<String> parameters = new ArrayList<String>();
		FrontController fc = new FrontController();
		ViewDispatcher v = new ViewDispatcher();
		if(NomeField.getText().equals(GestioneSessione.getNomeOperatore()) &&
			CognomeField.getText().equals(GestioneSessione.getCognomeOperatore()) &&
			TelefonoField.getText().equals(GestioneSessione.getTelefonoOperatore()) &&
			IndirizzoField.getText().equals(GestioneSessione.getIndirizzoOperatore()) ) 
			v.showMessage(2, "Informazione", 
					"I dati inseriti sono gli stessi già presenti nel sistema");
		else {
			parameters.add(NomeField.getText());
			parameters.add(CognomeField.getText());
			parameters.add(TelefonoField.getText());
			parameters.add(IndirizzoField.getText());
			if( (boolean)fc.handleRequest("ModificaDatiOperatore",parameters))
				v.showMessage(0, "Informazione", "Modifica dati avvenuta con successo");
			else
				v.showMessage(1, "Errore", "Purtroppo la modifica dei dati non è riuscita");
			
		}	
			
	}
	
	@FXML
	public void Indietro(Event e){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}

}
