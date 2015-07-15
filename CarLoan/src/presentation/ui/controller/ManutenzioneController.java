package presentation.ui.controller;

import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.InputController;

/**
 * Classe di controllo per l'interfaccia Manutenzione
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class ManutenzioneController {
	@FXML
	private TextField targa;
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void conferma(){
		ViewDispatcher v = new ViewDispatcher();
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		
		if(!targa.getText().isEmpty()){
			if(InputController.targaVerify(targa.getText())) {
				Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", 
					"Sei sicuro di voler continuare?");
			
				if(confirm.isPresent() && confirm.get() == ButtonType.OK) {
					parameters.add(targa.getText());

					if((boolean)fc.handleRequest("InserisciManutenzione",parameters)) {
						v.showMessage(0, "Informazione", "Operazione completata con successo");
						fc.handleRequest("MenuOperatore");
					}
					else
						v.showMessage(1, "Errore", "L'operazione non è stata completata. \n"
								+ "Assicurati di aver inserito ll targa corretta.\n"
								+ "E' possibile che l'auto identificata da targa: " 
								+ targa.getText() + " sia in noleggio");
				}
			}
			else
				v.showMessage(1, "Errore", "Formato targa non valido");
		}
		else
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci la targa dell'auto");
		
		
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
		
	}
}
