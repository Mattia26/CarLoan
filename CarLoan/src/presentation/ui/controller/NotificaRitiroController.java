package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NotificaRitiroController {
	
	@FXML
	private TextField id;
	
	@FXML
	public void conferma(){
		ViewDispatcher v = new ViewDispatcher();
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		
		if(!id.getText().isEmpty()){
			parameters.add(id.getText());
			if((boolean)fc.handleRequest("NotificaRitiro",parameters))
				v.showMessage(0, "Informazione", "Operazione completata con successo");
			else
				v.showMessage(1, "Errore", "L'operazione non � stata completata. \n"
						+ "Assicurati di aver inserito l'id correttamente.");
		}
		else{
			
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci l'id del contratto");
			
		}
		
		
	}
	
	@FXML
	public void indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
		
	}

}
