package presentation.ui.controller;


import java.util.ArrayList;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ModificaIdController {
	
	@FXML
	private TextField id;
	
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	@FXML
	public void Ok(){
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		ViewDispatcher v = new ViewDispatcher();
		
		if(id.getText() != null){
			parameters.add(id.getText());
			GestioneSessione.setId(id.getText());
			if(fc.handleRequest("GetDatiContratto").getClass().equals(Boolean.class))
				v.showMessage(1, "Errore!" ,
				"Nessun contratto ritrovato con tale id. "
				+ "\nAssicurati di aver inserito l'id corretto e riprova");	
			else
				fc.handleRequest("ModificaContratto");
		}
			
		else{		
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci l'id del contratto");	
		}
	}
}
