package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChiudiContrattoController implements Initializable{
	
	@FXML
	private TextField idContratto,chilometri;
	
	@FXML
	private ChoiceBox<String> chilometraggio;
	
	@FXML
	private Label kmPercorsi,saldo;
	
	@FXML
	private Button conferma;
	
	private ArrayList<String> parameters;
	
	
	@FXML
	public void indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	@FXML
	public void setFields(){
		if(chilometraggio.getValue() == "Illimitato"){
			kmPercorsi.setVisible(true);
			chilometri.setVisible(true);
		}
		else if (chilometraggio.getValue() == "Limitato"){
			kmPercorsi.setVisible(false);
			chilometri.setVisible(false);
		}
	}
	
	@FXML
	public void Calcola(){
		if(chilometraggio.getValue() == null || (chilometraggio.getValue() == "Illimitato" && 
				chilometri.getText().isEmpty()) || idContratto.getText().isEmpty()  ){
			ViewDispatcher v = new ViewDispatcher();
			v.showMessage(1,"Errore!", "Completare tutti i campi!");
		}
		else{
			FrontController fc = new FrontController();
			parameters = new ArrayList<String>();
			parameters.add(idContratto.getText());
			parameters.add(chilometraggio.getValue());
			parameters.add(chilometri.getText());
			saldo.setText(Double.toString((double)fc.handleRequest("CalcolaSaldo",parameters)));
			
			conferma.setVisible(true);
		}
	}
	
	@FXML
	public void Conferma(){
		
		FrontController fc = new FrontController();
		ViewDispatcher v = new ViewDispatcher();

		if((boolean)fc.handleRequest("ChiudiContratto", parameters)){
			v.showMessage(0, "Informazione", "Operazione completata con successo");
			fc.handleRequest("MenuOperatore");
		}
		else
			v.showMessage(1, "Errore", "Impossibile completare l'operazione.\n"
					+ " Assicurati di aver inserito l'id correttamente");
		
			
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chilometraggio.setItems(FXCollections.observableArrayList("Limitato","Illimitato"));
		
	}
	


}
