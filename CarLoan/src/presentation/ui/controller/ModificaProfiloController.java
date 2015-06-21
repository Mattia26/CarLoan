package presentation.ui.controller;



import java.util.ArrayList;

import presentation.FrontController;
import presentation.GestioneSessione;
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
	
	@FXML
	public void setFields(Event e){
		NomeField.setText(GestioneSessione.getNomeOperatore());
		CognomeField.setText(GestioneSessione.getCognomeOperatore());
		TelefonoField.setText(GestioneSessione.getTelefonoOperatore());
	}
	
	@FXML
	public void Modifica(Event e){
		ArrayList<String> parameters = new ArrayList<String>();
		FrontController fc = new FrontController();
		if(NomeField.getText() != GestioneSessione.getNomeOperatore() | CognomeField.getText() != GestioneSessione.getCognomeOperatore() | TelefonoField.getText() != GestioneSessione.getTelefonoOperatore()){
			parameters.add(NomeField.getText());
			parameters.add(CognomeField.getText());
			parameters.add(TelefonoField.getText());
			fc.handleRequest("ModificaDatiOperatore",parameters);
			
		}
	}
	
	@FXML
	public void Indietro(Event e){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}

}
