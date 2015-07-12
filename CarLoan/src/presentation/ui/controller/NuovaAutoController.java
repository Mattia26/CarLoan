package presentation.ui.controller;

import java.net.URL;
import java.time.DateTimeException;
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
	
	@FXML
	public void conferma(){
		if(targa.getText().isEmpty() || modello.getText().isEmpty() || fascia.getValue().isEmpty()
				|| manutenzione.getText().isEmpty() || km.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi!");
		else{
			try {
				if(!InputController.dateVerify(manutenzione.getText()))
					v.showMessage(1, "Errore", "La data di manutenzione inserita non � valida!");
				else{
					try{
						Double.parseDouble(km.getText());
				
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
					catch(NumberFormatException e){
					v.showMessage(1, "Errore", "Il valore del chilometraggio non è valido!");
					}
				}
				
			}
			catch (DateTimeException e) {
				v.showMessage(1, "Errore", "La data di manutenzione inserita � inesistente.");
				manutenzione.setText("");
			}
		
		}
	}
	
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fascia.setItems(FXCollections.observableArrayList("A","B","C"));
	}
}
