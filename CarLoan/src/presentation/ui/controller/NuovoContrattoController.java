package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia NuovoContratto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
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
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void Conferma(){
		if(targa.getText().isEmpty() || dataInizio.getText().isEmpty() || dataFine.getText().isEmpty()
				|| sede.getValue() == null || tipo.getValue() == null || chilometraggio.getValue() == null
				|| nomeC.getText().isEmpty() || cognomeC.getText().isEmpty() || codiceFiscale.getText().isEmpty()
				|| acconto.getText().isEmpty() || telefono.getText().isEmpty()){
			ViewDispatcher v = new ViewDispatcher();
			v.showMessage(1, "Errore", "Completare tutti i campi!");
		}
		else{
			
			ViewDispatcher w = new ViewDispatcher();
			
			
			if(!InputController.codiceFiscaleVerify(codiceFiscale.getText()))
				w.showMessage(1, "Errore", "Il codice fiscale non Ãš corretto!");
			else if(!InputController.telVerify(telefono.getText()))
				w.showMessage(1, "Errore", "Il numero di telefono non Ãš corretto!");
			else{
				try {
					Integer.parseInt(acconto.getText());
					ArrayList<String> parameters = new ArrayList<String>();
					parameters.add(targa.getText());
					parameters.add(dataInizio.getText());
					parameters.add(dataFine.getText());
					parameters.add(sede.getValue());
					parameters.add(tipo.getValue());
					parameters.add(chilometraggio.getValue());
					parameters.add(codiceFiscale.getText());
					parameters.add(acconto.getText());
					FrontController fc = new FrontController();
					
					
					ArrayList<String> parametersC = new ArrayList<String>();
					parametersC.add(nomeC.getText());
					parametersC.add(cognomeC.getText());
					parametersC.add(telefono.getText());
					parametersC.add(codiceFiscale.getText());
					
					if(!(boolean)fc.handleRequest("InserisciCliente",parametersC)) {
						Optional<ButtonType> confirm =  w.showMessage(2, "Attenzione", 
								"C'è già un cliente con tale codice fiscale.\n "
								+ "I dati del cliente verranno modificati con quelli inseriti.");

						if(confirm.isPresent() && confirm.get() == ButtonType.OK){
							if(!(boolean)fc.handleRequest("ModificaDatiCliente",parametersC)) {
								w.showMessage(1, "Errore", 
										"Impossibile modificare i dati del cliente");
								return;
							}
							
							int id = (int)fc.handleRequest("NuovoContratto",parameters);
							if(id != -1){
									w.showMessage(0, "Avviso","Contratto stipulato con successo. \n"
										+ "Id contratto: " + id);
								fc.handleRequest("MenuOperatore");
							}
							else{
								w.showMessage(1, "Errore", "L'operazione non è stata effettuata");
							}
					
						}
					}
				}
				catch(NumberFormatException e) {
					w.showMessage(2,"Attenzione!", "L'acconto deve essere un numero intero!");
					}
			}
		}
	}
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("CercaAuto");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		targa.setText(GestioneSessione.getTarga());
		dataInizio.setText(GestioneSessione.getDataInizio());
		dataFine.setText(GestioneSessione.getDataFine());
		sede.setItems(FXCollections.observableArrayList("Milano","Brescia","Napoli"));
		tipo.setItems(FXCollections.observableArrayList("Giornaliera","Settimanale"));
		chilometraggio.setItems(FXCollections.observableArrayList("Limitato","Illimitato"));
		
	}
}