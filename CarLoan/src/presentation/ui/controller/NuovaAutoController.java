package presentation.ui.controller;

import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia NuovaAuto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class NuovaAutoController implements Initializable{
	
	@FXML
	private TextField targa;
	
	@FXML
	private TextField modello;
	
	@FXML
	private ChoiceBox<String> fascia;
	
	@FXML
	private TextField manutenzione;
	
	@FXML
	private TextField km;
	
	private FrontController fc = new FrontController();
	
	private ViewDispatcher v = new ViewDispatcher();
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void conferma(){
		if(targa.getText().isEmpty() || modello.getText().isEmpty() || fascia.getValue().isEmpty()
				|| manutenzione.getText().isEmpty() || km.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi!");
		else{
			
			if(!InputController.targaVerify(targa.getText()))
				v.showMessage(1, "Attenzione", "La targa inserita non è valida!");
				
			else if(!InputController.dateVerify(manutenzione.getText()))
				v.showMessage(1, "Attenzione", "La data di manutenzione inserita non è valida!");
			else if(InputController.getDate(manutenzione.getText()).isAfter
					(LocalDate.now().plusYears(1)))
				v.showMessage(1, "Attenzione", "La data di manutenzione inserita non è valida!"
					+ "Essa deve essere entro il: " + LocalDate.now().plusYears(1));
			else if(!InputController.modelloVerify(modello.getText())) 
				v.showMessage(1, "Attenzione", "La lunghezza del campo modello non è valida\n" +
			"Esso deve contenere tra 4 e 20 caratteri.");
			else if(!InputController.ultimoKmVerify(km.getText()))
				v.showMessage(1, "Attenzione", "Ultimo chilometraggio non valido! Deve essere"
						+ "un intero positivo.");
			
			else{
				ArrayList<String> parameters = new ArrayList<String>();
				parameters.add(targa.getText());
				parameters.add(modello.getText());
				parameters.add(fascia.getValue());
				parameters.add(manutenzione.getText());
				parameters.add(km.getText());
		
				if((boolean)fc.handleRequest("NuovaAuto",parameters)){
					v.showMessage(0, "Informazione", "Operazione completata con successo");
					fc.handleRequest("MenuAmministratore");
				}
				else
					v.showMessage(1, "Errore", "L'operazione non è riuscita");
			}		
			
		}
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fascia.setItems(FXCollections.observableArrayList("A","B","C"));
	}
}
