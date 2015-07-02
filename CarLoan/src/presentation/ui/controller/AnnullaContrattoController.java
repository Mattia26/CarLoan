package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AnnullaContrattoController {
	
	@FXML
	private TextField id;
	
	private ViewDispatcher v = new ViewDispatcher();
	
	@FXML
	public void indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	@FXML
	public void conferma(){
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		
		if(id.getText() != null){
			parameters.add(id.getText());
			if((boolean)fc.handleRequest("AnnullaContratto",parameters))
				v.showMessage(0, "Informazione", "Operazione completata con successo");
			else
				v.showMessage(1, "Errore", "L'operazione non è stata completata");
		}
		else{
			
			v.showMessage(1, "Errore!", "Campo vuoto");
			
		}
	}

}
