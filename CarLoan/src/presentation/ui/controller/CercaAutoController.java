package presentation.ui.controller;


import java.util.ArrayList;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;

import utility.InputController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class CercaAutoController {
	
	@FXML
	private ChoiceBox TipoBox = new ChoiceBox();
	
	@FXML
	private TextField Dal = new TextField(),Al = new TextField();
	
	@FXML
	private ListView vista = new ListView();
	
	@FXML
	private Button avanti;
	
	private InputController v;
	
	
	@SuppressWarnings("unchecked")
	@FXML
	public void TipeClick(){
		TipoBox.setItems(FXCollections.observableArrayList("A","B","C"));
	}
	
	
	
	@SuppressWarnings("unchecked")
	@FXML
	public void Cerca(){

		ViewDispatcher dispatcher = new ViewDispatcher();
		v = new InputController();
		
		
	
		if(Dal.getText().isEmpty() && Al.getText().isEmpty() && TipoBox.getValue() == null){
			
			dispatcher.showMessage(1, "Errore", "Inserire almeno un tipo o data di inizio e fine noleggio");
		}
		else if(TipoBox.getValue()==null && (!v.dateVerify(Dal.getText()) || !v.dateVerify(Al.getText()))){
			Dal.setText("");
			Al.setText("");
			dispatcher.showMessage(1, "Errore", "Le date inserite non sono corrette");
		}
		else{
			
			FrontController fc = new FrontController();
			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add((String)TipoBox.getValue());
			parameters.add(Dal.getText());
			parameters.add(Al.getText());
			@SuppressWarnings("unchecked")
			ArrayList<String> result = (ArrayList<String>)fc.handleRequest("CercaAuto", parameters);
			if(result.isEmpty())
				dispatcher.showMessage(0, "Nessun risultato!", "Ricontrollare i dati inseriti");
			vista.setItems(FXCollections.observableArrayList(result));
			
		}
	}
	
	@FXML
	public void setAvanti(){
		if(vista.getSelectionModel().getSelectedItem() != null)
			avanti.setVisible(true);	
	}
	
	@FXML
	public void Avanti(){
		String selezione = (String)vista.getSelectionModel().getSelectedItem();
		String[] targa = selezione.split(" ");
		GestioneSessione.setTarga(targa[0]);
		FrontController fc = new FrontController();
		fc.handleRequest("NuovoContratto");
		
	}
	
	@FXML
	public void Indietro(){
		FrontController fc = new FrontController();
		fc.handleRequest("MenuOperatore");
	}
	
	
	


}
