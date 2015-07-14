package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
			
			double conto = (double)fc.handleRequest("CalcolaSaldo",parameters);
			
			if(conto == -1){
				v.showMessage(0, "Errore", "Operazione fallita.\n Controllare i campi e riprovare.");
			}
			else{
				saldo.setText(Double.toString(conto));
				conferma.setVisible(true);
			}
			
			
		}
	}
	
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
				v.showMessage(1, "Errore", "Impossibile completare l'operazione.\n"
						+ " Assicurati di aver inserito l'id correttamente");
			
		}
	}

}
