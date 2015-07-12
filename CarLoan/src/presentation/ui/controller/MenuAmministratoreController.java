package presentation.ui.controller;

import javafx.fxml.FXML;
import presentation.FrontController;

public class MenuAmministratoreController {
	
	private FrontController fc = new FrontController();
	
	@FXML
	public void nuovoOperatore(){
		fc.handleRequest("NuovoOperatore");
	}
	
	@FXML
	public void eliminaOperatore(){
		fc.handleRequest("EliminaOperatore");
	}
	
	@FXML
	public void nuovaAuto(){
		fc.handleRequest("NuovaAuto");
	}
	
	@FXML
	public void eliminaAuto(){
		fc.handleRequest("EliminaAuto");
	}
	
	@FXML
	public void modificaPrezzi(){
		fc.handleRequest("ModificaPrezzi");
	}

}
