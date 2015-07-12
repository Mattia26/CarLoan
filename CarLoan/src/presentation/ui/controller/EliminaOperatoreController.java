package presentation.ui.controller;

import java.util.ArrayList;
import java.util.Optional;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class EliminaOperatoreController {
	
	@FXML
	private TextField nick;
	
	private FrontController fc = new FrontController();
	
	private ViewDispatcher v = new ViewDispatcher();
	
	@FXML
	public void conferma(){
		Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", "Le modifiche saranno "
				+ "permanenti, si è sicuri di voler continuare?");
		
		if(confirm.isPresent() && confirm.get() == ButtonType.OK){
		if(nick.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi");
		else{
			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add(nick.getText());
			
			if((boolean)fc.handleRequest("EliminaOperatore",parameters)){
				v.showMessage(0, "Informazione", "Operazione completata con successo!");
				fc.handleRequest("MenuAmministratore");
			}
			else
				v.showMessage(1, "Errore", "Operazione non completata!\n"
						+ "controllare il nickname e riprovare!");
		}
		}	
		
	}
	
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
	}

}
