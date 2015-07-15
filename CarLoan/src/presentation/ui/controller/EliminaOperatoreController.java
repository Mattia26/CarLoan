package presentation.ui.controller;

import java.util.ArrayList;
import java.util.Optional;

import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.LoginUtility;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia EliminaOperatore
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class EliminaOperatoreController {
	
	@FXML
	private TextField nick;
	
	private FrontController fc = new FrontController();
	
	private ViewDispatcher v = new ViewDispatcher();
	
	/**
	 * Gestore per l'esecuzione delle operazioni alla pressione del tasto Conferma
	 */
	@FXML
	public void conferma(){
		
		if(nick.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi");
		else{
			Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", "Le modifiche saranno "
					+ "permanenti, si è sicuri di voler continuare?");
			
			if(confirm.isPresent() && confirm.get() == ButtonType.OK){
				ArrayList<String> parameters = new ArrayList<String>();
				parameters.add(nick.getText());
				
				LoginUtility l = new LoginUtility();

				if(l.deleteUser("operatore" + parameters.get(0))) {
					if((boolean)fc.handleRequest("EliminaOperatore",parameters)){
						v.showMessage(0, "Informazione", "Operazione completata con successo!");
						fc.handleRequest("MenuAmministratore");
					}	
					else
						v.showMessage(1, "Errore", "Operazione non completata!\n"
								+ "controllare il nickname e riprovare!");
				}
				else
					v.showMessage(1, "Errore", "Operazione non completata!\n"
							+ "Nessun operatore associato a tale nickname.");
			
			}
		}	
		
	}
	
	/**
	 * Gestore per le operazioni all'esecuzione del tasto Indietro
	 */
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
	}

}
