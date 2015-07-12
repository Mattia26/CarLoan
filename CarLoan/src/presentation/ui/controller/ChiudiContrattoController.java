package presentation.ui.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;




import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChiudiContrattoController{
	
	@FXML
	private TextField idContratto,chilometri;
	

	
	@FXML
	private Label kmPercorsi,saldo;
	
	@FXML
	private Button conferma;
	
	private ArrayList<String> parameters;
	
	private ViewDispatcher v = new ViewDispatcher();
	
	private FrontController fc = new FrontController();
	
	@FXML
	public void indietro(){
		fc.handleRequest("MenuOperatore");
	}
	
	
	
	@FXML
	public void Calcola(){
		if( chilometri.getText().isEmpty() || idContratto.getText().isEmpty()  ){
			v.showMessage(1,"Errore!", "Completare tutti i campi!");
		}
		else{
			parameters = new ArrayList<String>();
			parameters.add(idContratto.getText());
			parameters.add(chilometri.getText());
			GestioneSessione.setId(idContratto.getText());
			ArrayList<String> datiContratto = 
					(ArrayList<String>)fc.handleRequest("GetDatiContratto");
			
			if(datiContratto.isEmpty())
				v.showMessage(1, "Errore!" ,
				"Nessun contratto ritrovato con tale id. "
				+ "\nAssicurati di aver inserito l'id corretto e riprova");	
			else if (ChronoUnit.DAYS.between(LocalDate.now(),
					InputController.getDate(datiContratto.get(1))) > 0)
				v.showMessage(1, "Errore!" ,
						"Impossibile chiudere un contratto non ancora iniziato. "
						+ "\nEsso inizierà il: " + datiContratto.get(1));	
			else {
				double conto = (double)fc.handleRequest("CalcolaSaldo",parameters);
				
				if(conto == -1){
					v.showMessage(0, "Errore", "Impossibile calcolare l'importo da saldare.\n "
							+ "Controllare i campi e riprovare.");
				}
				else{
					saldo.setText(Double.toString(conto));
					conferma.setVisible(true);
				}	
			}
			
		}
	}
	
	@FXML
	public void Conferma(){
		

		if((boolean)fc.handleRequest("ChiudiContratto", parameters)){
			v.showMessage(0, "Informazione", "Operazione completata con successo");
			fc.handleRequest("MenuOperatore");
		}
		else
			v.showMessage(1, "Errore", "Impossibile completare l'operazione.\n"
					+ " Assicurati di aver inserito l'id correttamente");
		
			
	}


	


}
