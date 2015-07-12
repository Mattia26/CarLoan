package presentation.ui.controller;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
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
			ArrayList<String> datiContratto = 
					(ArrayList<String>)fc.handleRequest("GetDatiContratto");
			
			if(datiContratto.isEmpty())
				v.showMessage(1, "Errore!" ,
				"Nessun contratto ritrovato con tale id. "
				+ "\nAssicurati di aver inserito l'id corretto e riprova");	
			else if (ChronoUnit.DAYS.between(LocalDate.now(),
					InputController.getDate(datiContratto.get(1))) < 3)
				v.showMessage(1, "Errore!" ,
						"Impossibile modificare il contratto. "
						+ "\nLa data di inizio è: " + datiContratto.get(1) + ". E' possibile "
						+ "modificare un contratto solo fino a 3 giorni prima del suo inizio");	
			else
				fc.handleRequest("ModificaContratto");
		}
			
		else{		
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci l'id del contratto");	
		}
	}
}
