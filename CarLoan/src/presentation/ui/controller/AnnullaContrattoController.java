package presentation.ui.controller;

import java.util.ArrayList;
import java.util.Optional;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
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
		
		Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", "Le modifiche saranno "
				+ "permanenti, si è sicuri di voler continuare?");
		
		if(confirm.isPresent() && confirm.get() == ButtonType.OK){
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		
		if(!id.getText().isEmpty()){
			parameters.add(id.getText());
			if((boolean)fc.handleRequest("AnnullaContratto",parameters))
				v.showMessage(0, "Informazione", "Operazione completata con successo");
			else
				v.showMessage(1, "Errore", "L'operazione non � stata completata. \n"
						+ "Assicurati di aver inserito l'id correttamente.");
		}
		else{
			
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci l'id del contratto");
			
		}
	}

	}
}
