package presentation.ui.controller;


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
		if(id.getText().isEmpty()){
			ViewDispatcher v = new ViewDispatcher();
			v.showMessage(1, "Errore", "Campo vuoto");
		}
		else{
	
			FrontController fc = new FrontController();
			GestioneSessione.setId(id.getText());
			fc.handleRequest("ModificaContratto");
		}
	}

}
