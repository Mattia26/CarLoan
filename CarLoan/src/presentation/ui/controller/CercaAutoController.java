package presentation.ui.controller;


import java.net.URL;
import java.time.DateTimeException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia CercaAuto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class CercaAutoController implements Initializable{
	
	@FXML
	private ChoiceBox TipoBox = new ChoiceBox();
	
	@FXML
	private TextField Dal = new TextField(),Al = new TextField();
	
	@FXML
	private ListView vista = new ListView();
	
	@FXML
	private Button avanti;
	
	/**
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TipoBox.setItems(FXCollections.observableArrayList("A","B","C", "Qualsiasi"));
		TipoBox.setValue("Qualsiasi");
	}
	

	@SuppressWarnings("unchecked")
	@FXML
	public void TipeClick(){
		TipoBox.setItems(FXCollections.observableArrayList("A","B","C", "Qualsiasi"));
	}
	
	
	
	@SuppressWarnings("unchecked")
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Cerca
	 */
	@FXML
	public void Cerca(){

		ViewDispatcher dispatcher = new ViewDispatcher();
		
	
		if(Dal.getText().isEmpty() || Al.getText().isEmpty()){
			
			dispatcher.showMessage(1, "Errore", "Inserire data di inizio e fine noleggio");
		}
		else {
			try {
				boolean correctDataInizio = InputController.dateVerify(Dal.getText());
				try {
					boolean correctDataFine = InputController.dateVerify(Dal.getText());
					if(! correctDataInizio || ! correctDataFine){
						Dal.setText("");
						Al.setText("");
						dispatcher.showMessage(1, "Errore",
								"La data di inizio e quella di fine devono essere "
								+ "posteriori a quella attuale");
					}
					
					else if(!InputController.dateVerify(Dal.getText(),Al.getText())) {
						Dal.setText("");
						Al.setText("");
						dispatcher.showMessage(1, "Errore", "La data di inizio deve essere "
							+ "non posteriore rispetto a quella di fine"); 
					}
				
					else {
						FrontController fc = new FrontController();
						ArrayList<String> parameters = new ArrayList<String>();
						parameters.add((String)TipoBox.getValue());
						parameters.add(Dal.getText());
						parameters.add(Al.getText());
						@SuppressWarnings("unchecked")
						ArrayList<String> result = (ArrayList<String>)fc.handleRequest("CercaAuto", parameters);
						if(result.isEmpty())
							dispatcher.showMessage(2, "Nessun risultato!", "Nessun'auto disponibile nell'intervallo di tempo selezionato.");
						vista.setItems(FXCollections.observableArrayList(result));
					}
				}
				catch (DateTimeException e) {
					dispatcher.showMessage(1, "Errore", "La data di fine inserita � inesistente.");
					Al.setText("");
				}
			}
			catch (DateTimeException e) {
				Dal.setText("");
				try {
					InputController.dateVerify(Al.getText());
					dispatcher.showMessage(1, "Errore", "La data di inizio inserita � inesistente.");
				}	
				catch (DateTimeException e2) {
					Al.setText("");
					dispatcher.showMessage(1, "Errore", "Entrambe le date inserite sono inesistenti.");
				}
			}
		}
			
	}
	
	/**
	 * Metodo che rende visibile il tasto Avanti alla selezione di un auto
	 * dopo la ricerca
	 */
	@FXML
	public void setAvanti(){
		if(vista.getSelectionModel().getSelectedItem() != null)
			avanti.setVisible(true);	
	}
	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Avanti
	 */
	@FXML
	public void Avanti(){
		String selezione = (String)vista.getSelectionModel().getSelectedItem();
		String[] targa = selezione.split(" ");
		GestioneSessione.setTarga(targa[0]);
		GestioneSessione.setDataInizio(Dal.getText());
		GestioneSessione.setDataFine(Al.getText());
		FrontController fc = new FrontController();
		fc.handleRequest("NuovoContratto");
		
	}
	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto indietro
	 */
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	
	


}
