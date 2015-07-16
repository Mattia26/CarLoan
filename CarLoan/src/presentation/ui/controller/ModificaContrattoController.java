package presentation.ui.controller;

import java.net.URL;
import java.time.LocalDate;
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
/**
 * Classe di controllo per l'interfaccia ModificaContratto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
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
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("ModificaId");
	}
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void Conferma(){
		
		if(targa.getText().isEmpty() || dataInizio.getText().isEmpty() || dataFine.getText().isEmpty()
			|| sede.getValue() == null || tipo.getValue() == null || chilometraggio.getValue() == null
			|| nomeC.getText().isEmpty() || cognomeC.getText().isEmpty() || codiceFiscale.getText().isEmpty()
			|| acconto.getText().isEmpty() || TelefonoCliente.getText().isEmpty()) {
			ViewDispatcher v = new ViewDispatcher();
			v.showMessage(1, "Errore", "Completare tutti i campi!");
		}
		
		
		else{
			ViewDispatcher w = new ViewDispatcher();
			
			if(!InputController.telVerify(TelefonoCliente.getText()))
					w.showMessage(1, "Errore", "Il numero di telefono non √® valido");
			else if(!InputController.accontoVerify(acconto.getText()))
				w.showMessage(1, "Errore", "Acconto non valido. Deve essere un intero positivo");
			
			else {
				
				if ( sede.getValue().equals(GestioneSessione.getSedeRestituzione()) &&
					tipo.getValue().charAt(0) == GestioneSessione.getTipoContratto().charAt(0) &&
					chilometraggio.getValue().charAt(0) == GestioneSessione.getTipologiaKmContratto().charAt(0) &&	
					acconto.getText().equals(GestioneSessione.getAcconto()) ) {
					
					ViewDispatcher vd = new ViewDispatcher();
					FrontController fc = new FrontController();
							
					if(TelefonoCliente.getText().equals(GestioneSessione.getTelefonoCliente()))
						vd.showMessage(2, "Attenzione", "Nessuna modifica apportata al contratto."
							+ "\n I dati inseriti sono gli stessi gi‡ presenti nel sistema.");
					else {
						ArrayList<String> parametersC = new ArrayList<String>();
						parametersC.add(nomeC.getText());
						parametersC.add(cognomeC.getText());
						parametersC.add(TelefonoCliente.getText());
						parametersC.add(codiceFiscale.getText());
						if((boolean)fc.handleRequest("ModificaDatiCliente", parametersC)) {
							vd.showMessage(2, "Informazione", "Nessuna modifica apportata al contratto."
							+ "\n Modifica apportata solo al numero di telefono del cliente.");
							fc.handleRequest("MenuOperatore");
						}
						else
							vd.showMessage(1, "Errore", "L'operazione non √® stata effettuata");
					}
			
				}
								
				else {	
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
							
					if((boolean)fc.handleRequest("ModificaContratto",parameters)) {
				
						if(TelefonoCliente.getText().equals(GestioneSessione.getTelefonoCliente()) ) {
							vd.showMessage(0, "Avviso","L'operazione √® stata effettuata con successo");
							fc.handleRequest("MenuOperatore");
						}
								
						else {
							ArrayList<String> parametersC = new ArrayList<String>();
							parametersC.add(nomeC.getText());
							parametersC.add(cognomeC.getText());
							parametersC.add(TelefonoCliente.getText());
							parametersC.add(codiceFiscale.getText());
							if((boolean)fc.handleRequest("ModificaDatiCliente", parametersC))
								vd.showMessage(0, "Avviso",
										"L'operazione √® stata effettuata con successo");
							else
								vd.showMessage(2, "Attenzione", "Impossibile modificare "
										+ "il numero di telefonodel cliente.\n" +
										"I dati del contratto sono stati comunque modificati");
										
								fc.handleRequest("MenuOperatore");
						}	
								
								
					}
					else 
						vd.showMessage(1, "Errore", "L'operazione non √® stata effettuata");	
				}
						
			}
				
		}
	}
	/**
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FrontController fc = new FrontController();
		ArrayList<String> dati = (ArrayList<String>)fc.handleRequest("GetDatiContratto");
		ArrayList<String> citt‡ = (ArrayList<String>)(fc.handleRequest("GetCitt‡Restituzione"));
		if(! citt‡.isEmpty()) 
			sede.setItems(FXCollections.observableArrayList(citt‡));
		else
			sede.setItems(FXCollections.observableArrayList(dati.get(3)));
		
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
		
		targa.setEditable(false);
		dataInizio.setEditable(false);
		dataFine.setEditable(false);
		nomeC.setEditable(false);
		cognomeC.setEditable(false);
		codiceFiscale.setEditable(false);
	}

}
