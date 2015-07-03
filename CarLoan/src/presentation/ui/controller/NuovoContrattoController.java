package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class NuovoContrattoController implements Initializable{
	
	@FXML
	private TextField targa;
	
	@FXML
	private TextField dataInizio;
	
	@FXML
	private TextField dataFine;
	
	@FXML
	private ChoiceBox<String> sede;
	
	@FXML
	private ChoiceBox<String> tipo;
	
	@FXML
	private ChoiceBox<String> chilometraggio;
	
	@FXML
	private TextField codiceFiscale;
	
	@FXML
	private TextField nomeC;
	
	@FXML
	private TextField cognomeC;
	
	@FXML
	private TextField acconto;
	
	@FXML
	private TextField telefono;

	@FXML
	public void Conferma(){
		if(targa.getText().isEmpty() || dataInizio.getText().isEmpty() || dataFine.getText().isEmpty()
				|| sede.getValue() == null || tipo.getValue() == null || chilometraggio.getValue() == null
				|| nomeC.getText().isEmpty() || cognomeC.getText().isEmpty() || codiceFiscale.getText().isEmpty()
				|| acconto.getText().isEmpty()){
			ViewDispatcher v = new ViewDispatcher();
			v.showMessage(1, "Errore", "Completare tutti i campi!");
		}
		else{
			
			ViewDispatcher w = new ViewDispatcher();
			
			if(!InputController.dateVerify(dataInizio.getText()) || !InputController.dateVerify(dataFine.getText())){
				
				w.showMessage(1, "Errore", "Le date non sono corrette!");
					
						
			}
			else if(!InputController.codiceFiscaleVerify(codiceFiscale.getText()))
				w.showMessage(1, "Errore", "Il codice fiscale non è corretto!");
			else{
				ArrayList<String> parameters = new ArrayList<String>();
				parameters.add(targa.getText());
				parameters.add(dataInizio.getText());
				parameters.add(dataFine.getText());
				parameters.add(sede.getValue());
				parameters.add(tipo.getValue());
				parameters.add(chilometraggio.getValue());
				parameters.add(nomeC.getText());
				parameters.add(cognomeC.getText());
				parameters.add(codiceFiscale.getText());
				parameters.add(acconto.getText());
				parameters.add(telefono.getText());
				FrontController fc = new FrontController();
				ViewDispatcher vd = new ViewDispatcher();
				if((int)fc.handleRequest("NuovoContratto",parameters) != -1){
					
					vd.showMessage(0, "Avviso","L'operazione è stata effettuata con successo");
					fc.handleRequest("MenuOperatore");
				}
				else{
					vd.showMessage(1, "Errore", "L'operazione non è stata effettuata");
				}
			}
		}
	}
	
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("CercaAuto");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		targa.setText(GestioneSessione.getTarga());
		dataInizio.setText(GestioneSessione.getDataInzio());
		dataFine.setText(GestioneSessione.getDataFine());
		sede.setItems(FXCollections.observableArrayList("Milano","Brescia","Napoli"));
		tipo.setItems(FXCollections.observableArrayList("Giornaliera","Settimanale"));
		chilometraggio.setItems(FXCollections.observableArrayList("Limitato","Illimitato"));
		
	}
}
