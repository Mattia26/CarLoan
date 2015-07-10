package presentation.ui.controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import presentation.FrontController;
import presentation.ViewDispatcher;

public class ManutenzioneController {
	@FXML
	private TextField targa;
	
	@FXML
	public void conferma(){
		ViewDispatcher v = new ViewDispatcher();
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		
		if(!targa.getText().isEmpty()){
			parameters.add(targa.getText());
			if((boolean)fc.handleRequest("InserisciManutenzione",parameters))
				v.showMessage(0, "Informazione", "Operazione completata con successo");
			else
				v.showMessage(1, "Errore", "L'operazione non � stata completata. \n"
						+ "Assicurati di aver inserito ll targa corretta.");
		}
		else{
			
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci la targa dell'auto");
			
		}
		
		
	}
	
	@FXML
	public void indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
		
	}
}
