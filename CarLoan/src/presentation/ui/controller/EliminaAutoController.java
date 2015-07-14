package presentation.ui.controller;

import java.util.ArrayList;
import java.util.Optional;

import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia EliminaAuto
 * @author mattia
 *
 */
public class EliminaAutoController {
	
	@FXML
	private TextField targa;
	
	private FrontController fc = new FrontController();
	
	private ViewDispatcher v = new ViewDispatcher();
	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void conferma(){
		
		if(targa.getText().isEmpty())
			v.showMessage(1, "Errore", "Campo vuoto!");
		else if(!InputController.targaVerify(targa.getText()))
			v.showMessage(1, "Errore", "Formato targa non valido!");
		else{
			Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", "Le modifiche saranno "
					+ "permanenti, si è sicuri di voler continuare?");
			
			if(confirm.isPresent() && confirm.get() == ButtonType.OK){
				ArrayList<String> parameters = new ArrayList<String>();
				parameters.add(targa.getText());
				
				if((boolean)fc.handleRequest("EliminaAuto", parameters)){
					v.showMessage(0, "Informazione", "Operazione completata con successo!");
					fc.handleRequest("MenuAmministratore");
				}
				else
					v.showMessage(1, "Errore", "Operazione fallita!\n"
							+ "Controllare la targa inserita e riprovare.");
				
			
			}	
		}
	}
	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
	}
}
