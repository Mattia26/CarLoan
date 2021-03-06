package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia ChiudiContratto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
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
	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void indietro(){
		fc.handleRequest("MenuOperatore");
	}
	
	
	/**
	 * Gestore delle operazioni eseguite all pressione del tasto Calcola
	 */
	@FXML
	public void Calcola(){
		if( chilometri.getText().isEmpty() || idContratto.getText().isEmpty()  ){
			v.showMessage(1,"Errore!", "Completare tutti i campi!");
		}
		else {
			
			if(!InputController.idContrattoVerify(idContratto.getText())) {
					v.showMessage(2,"Attenzione!", "Id non valido! Esso deve "
							+ "essere un intero positivo");
					return;
			}
			
			else if(!InputController.ultimoKmVerify(chilometri.getText())){
						v.showMessage(2,"Attenzione!", "Ultimo chilometraggio non valido!"
								+ " Esso deve essere un intero positivo");
						return;
			}
					
			parameters = new ArrayList<String>();
			parameters.add(idContratto.getText());
			parameters.add(chilometri.getText());
					
			double conto = (double)fc.handleRequest("CalcolaSaldo",parameters);
				
			if(conto == -1)
				v.showMessage(1, "Errore", "Operazione fallita.\n "
								+ "Controllare l'id inserito e riprovare.");
			else if(conto == -2)
				v.showMessage(1, "Errore", "Nessun contratto attualmente in corso"
						+ " ritrovato con tale id.");
			else if(conto == -3)
				v.showMessage(1, "Errore", "L'ultimo chilometraggio inserito è errato:"
						+ "esso è minore dell'ultimo chilometraggio registrato!");
			else{
						saldo.setText("è " + Double.toString(conto));
						conferma.setVisible(true);
						idContratto.setEditable(false);
						chilometri.setEditable(false);
			}			
		}
	}
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void Conferma(){
		
		Optional<ButtonType> confirm =  v.showMessage(2, "Attenzione", 
				"Sei sicuro di voler continuare?");
		
		if(confirm.isPresent() && confirm.get() == ButtonType.OK) {
			
			if((boolean)fc.handleRequest("ChiudiContratto", parameters)){
				v.showMessage(0, "Informazione", "Operazione completata con successo");
				fc.handleRequest("MenuOperatore");
			}
		
			else
				v.showMessage(1, "Errore", "Siamo spiacenti. Operazione non riuscita. Riprova.");
			
		}
	}

}
