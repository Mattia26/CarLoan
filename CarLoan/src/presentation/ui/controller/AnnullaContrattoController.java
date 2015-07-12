package presentation.ui.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class AnnullaContrattoController {
	
	@FXML
	private TextField id;
	
	private ViewDispatcher v = new ViewDispatcher();
	
	@FXML
	public void indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	@FXML
	public void conferma(){
		
		Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", "Le modifiche saranno "
				+ "permanenti, si Ã¨ sicuri di voler continuare?");
		
		if(confirm.isPresent() && confirm.get() == ButtonType.OK){
		FrontController fc = new FrontController();
		ArrayList<String> parameters = new ArrayList<String>();
		
		if(!id.getText().isEmpty()){
			parameters.add(id.getText());
			GestioneSessione.setId(id.getText());
			
			ArrayList<String> datiContratto = 
					(ArrayList<String>)fc.handleRequest("GetDatiContratto");
			if(datiContratto.isEmpty())
				v.showMessage(1, "Errore", "Nessun contratto in corso ritrovato con l'id inserito. \n"
						+ "Assicurati di aver inserito l'id correttamente.");
			
			else if (ChronoUnit.DAYS.between(LocalDate.now(),
					InputController.getDate(datiContratto.get(1))) <= 0)
				v.showMessage(1, "Errore!" ,
						"Impossibile modificare il contratto. "
						+ "\nEsso è già iniziato il: " + datiContratto.get(1) + ". E' possibile "
						+ "modificare un contratto solo fino a 3 giorni prima del suo inizio");
			else if (ChronoUnit.DAYS.between(LocalDate.now(),
					InputController.getDate(datiContratto.get(1))) < 3)
				v.showMessage(1, "Errore!" ,
						"Impossibile modificare il contratto. "
						+ "\nLa data di inizio è: " + datiContratto.get(1) + ". E' possibile "
						+ "modificare un contratto solo fino a 3 giorni prima del suo inizio");	
			else {
				if((boolean)fc.handleRequest("AnnullaContratto",parameters))
					v.showMessage(0, "Informazione", "Operazione completata con successo");
				else
					v.showMessage(1, "Errore", "L'operazione non è stata completata. \n"
							+ "Assicurati di aver inserito l'id correttamente.");
			}
			
		}
		else{
			
			v.showMessage(1, "Errore!", "Campo vuoto. Per favore inserisci l'id del contratto");
			
		}
	}

	}
}
