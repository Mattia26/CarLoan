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

public class ModificaContrattoController implements Initializable{
	
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
	private TextField TelefonoCliente;
	
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("ModificaId");
	}
	
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
			InputController i = new InputController();
			ViewDispatcher w = new ViewDispatcher();
			
			if(!i.dateVerify(dataInizio.getText()) || !i.dateVerify(dataFine.getText())){
				
				w.showMessage(1, "Errore", "Le date non sono corrette!");
					
						
			}
			else if(!i.codiceFiscaleVerify(codiceFiscale.getText()))
				w.showMessage(1, "Errore", "Il codice fiscale non è corretto!");
			else{
				ArrayList<String> parameters = new ArrayList<String>();
				parameters.add(GestioneSessione.getId());
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
				FrontController fc = new FrontController();
				ViewDispatcher vd = new ViewDispatcher();
				if((boolean)fc.handleRequest("ModificaContratto",parameters)){
					
					vd.showMessage(0, "Avviso","L'operazione è stata effettuata con successo");
					fc.handleRequest("MenuOperatore");
				}
				else{
					vd.showMessage(1, "Errore", "L'operazione non è stata effettuata");
				}
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FrontController fc = new FrontController();
		ArrayList<String> dati = (ArrayList<String>)fc.handleRequest("GetDatiContratto");
		
		sede.setItems(FXCollections.observableArrayList("Milano","Brescia","Napoli"));
		tipo.setItems(FXCollections.observableArrayList("Giornaliera","Settimanale"));
		chilometraggio.setItems(FXCollections.observableArrayList("Limitato","Illimitato"));
		targa.setText(dati.get(0));
		dataInizio.setText(dati.get(1));
		dataFine.setText(dati.get(2));
		sede.setValue(dati.get(3));
		tipo.setValue(dati.get(4));
		chilometraggio.setValue(dati.get(5));
		nomeC.setText(dati.get(6));
		cognomeC.setText(dati.get(7));
		codiceFiscale.setText(dati.get(8));
		acconto.setText(dati.get(9));
		TelefonoCliente.setText(dati.get(10));
	}

}
