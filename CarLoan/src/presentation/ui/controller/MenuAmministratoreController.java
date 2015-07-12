package presentation.ui.controller;

import javafx.fxml.FXML;
import presentation.FrontController;

public class MenuAmministratoreController {
	
	private FrontController fc = new FrontController();
	
	@FXML
	public void nuovoOperatore(){
		fc.handleRequest("NuovoOperatore");
	}

}
