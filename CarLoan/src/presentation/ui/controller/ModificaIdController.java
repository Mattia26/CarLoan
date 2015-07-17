package presentation.ui.controller;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia ModificaId
 * @author Mattia Menna
 * @author Giuseppe Onesto
 *
 */
public class ModificaIdController {
	
	@FXML
	private TextField id;
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Ok
	 */
	@FXML
	public void Ok(){
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		ViewDispatcher v = new ViewDispatcher();
		
		if(id.getText() != null){
			if(!InputController.idContrattoVerify(id.getText()))
				v.showMessage(1, "Attenzione", 
						"Id inserito non valido. Deve essere un intero positivo");
			
			else {
				parameters.add(id.getText());
				GestioneSessione.setId(Integer.parseInt(id.getText()));
				ArrayList<String> datiContratto = 
						(ArrayList<String>)fc.handleRequest("GetDatiContratto");
				
				if(datiContratto.isEmpty())
					v.showMessage(1, "Errore!" ,
					"Nessun contratto ritrovato con tale id. "
					+ "\nAssicurati di aver inserito l'id corretto e riprova");	
				else if(InputController.getDate(datiContratto.get(1)).isBefore(LocalDate.now()))
					v.showMessage(1, "Errore!" ,
						"Impossibile modificare il contratto. Esso è un contratto già iniziato");
				else if(InputController.getDate(datiContratto.get(1)).minusDays(3).isBefore
						(LocalDate.now()))
					v.showMessage(1, "Errore!" ,
							"Impossibile modificare il contratto. "
							+ "\nLa data di inizio è: " + datiContratto.get(1) + ". E' possibile "
							+ "modificare un contratto solo fino a 3 giorni prima del suo inizio");	
				else 
					fc.handleRequest("ModificaContratto");
			}
		
			
		}
			
		else{		
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci l'id del contratto");	
		}
	}
}
