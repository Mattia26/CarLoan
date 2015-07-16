package presentation.ui.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
/**
 * Classe di controllo dell'intefaccia AnnullaContratto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class AnnullaContrattoController {
	
	@FXML
	private TextField id;
	
	/**
	 * Gestore delle interfacce
	 */
	private ViewDispatcher v = new ViewDispatcher();
	
	/**
	 * Gestore delle operazioni alla pressione del tasto indietro
	 */
	@FXML
	public void indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	/**
	 * Gestore delle operazioni alla pressione del tasto conferma
	 */
	@FXML
	public void conferma(){
		
		if(!id.getText().isEmpty()){
			try {
				if(Integer.parseInt(id.getText())<0) {
					v.showMessage(2,"Attenzione!", "Id non valido!"
							+ " Esso deve essere un intero positivo");
					return;
				}
				FrontController fc = new FrontController();
				ArrayList<String> parameters = new ArrayList<String>();
				
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
						"Impossibile annullare il contratto. Esso è un contratto già iniziato");
				else if(InputController.getDate(datiContratto.get(1)).minusDays(3).isBefore
						(LocalDate.now()))
					v.showMessage(1, "Errore!" ,
							"Impossibile annullare il contratto. "
							+ "\nLa data di inizio è: " + datiContratto.get(1) + ". E' possibile "
							+ "modificare un contratto solo fino a 3 giorni prima del suo inizio");
				
				else {
					Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", 
							"Le modifiche saranno permanenti, si Ã¨ sicuri di voler continuare?");
				
					if(confirm.isPresent() && confirm.get() == ButtonType.OK){
						
						if((boolean)fc.handleRequest("AnnullaContratto",parameters)){
							v.showMessage(0, "Informazione", "Operazione completata con successo");
							fc.handleRequest("MenuOperatore");
						}
						else
							v.showMessage(1, "Errore", "C'è stato un errore nell'operazione."
									+ "Riprova.");
					}
				
				}
			}
			catch(NumberFormatException e) {
				v.showMessage(1,"Errore!", "L'id deve essere un numero intero!");
				}
			
		}
			
		else
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci l'id del contratto");

	}
	
}
