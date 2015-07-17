package presentation.ui.controller;

import javafx.fxml.FXML;
import presentation.FrontController;
/**
 * Classe di controllo per l'interfaccia menuAmministratore
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class MenuAmministratoreController {
	
	private FrontController fc = new FrontController();
	

	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Nuovo Operatore
	 */
	@FXML
	public void nuovoOperatore(){
		fc.handleRequest("NuovoOperatore");
	}
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Elimina Operatore
	 */
	@FXML
	public void eliminaOperatore(){
		fc.handleRequest("EliminaOperatore");
	}
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Nuova Auto
	 */
	@FXML
	public void nuovaAuto(){
		fc.handleRequest("NuovaAuto");
	}
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Elimina Auto
	 */
	@FXML
	public void eliminaAuto(){
		fc.handleRequest("EliminaAuto");
	}
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Modifica Prezzi
	 */
	@FXML
	public void modificaPrezzi(){
		fc.handleRequest("ModificaPrezzi");
	}
	/**
	 * Gestore delle operazioni eseguite alla pressione della Label Logout
	 */
	@FXML
	public void logout(){
		fc.handleRequest("Logout");
	}

}
