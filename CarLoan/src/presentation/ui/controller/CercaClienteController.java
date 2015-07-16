package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia CercaCliente
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class CercaClienteController {
	
	private ViewDispatcher v = new ViewDispatcher();
	
	private FrontController fc = new FrontController();
	
	@FXML
	private TextField cf;
	
	@FXML
	private Label nome;
	
	@FXML
	private Label cognome;
	
	@FXML
	private Label telefono;
	
	@FXML
	private Button conf;
	
	@FXML
	private Button mdf;
	
	@FXML
	private TextField campotel;
	
	
	/**
	 * Lista di attributi riguardanti il cliente cercato
	 */
	private ArrayList<String> cliente;
	
	/**
	 * Gestore delle operazioni eseguite alla pressione del tasto Cerca
	 */
    @FXML
    public void cerca(){
    	ArrayList<String> parameter = new ArrayList<String>();
    	
    	
    	
    	if(cf.getText().isEmpty()){
    		v.showMessage(1, "Errore", "Campo vouto!");
    	}
    	else{
    	
    		if(!InputController.codiceFiscaleVerify(cf.getText()))
				v.showMessage(1, "Errore", "Il codice fiscale non � corretto!");
    		
    		else {
    			parameter.add(cf.getText());
    			cliente = (ArrayList<String>)fc.handleRequest("CercaCliente",parameter);
    			if(cliente.isEmpty()) {
    				v.showMessage(1, "Errore", "Cliente non trovato, riprovare!");
    			}
    		
    			else {
    				nome.setText(cliente.get(0));
    				cognome.setText(cliente.get(1));
    				telefono.setText(cliente.get(2));
    				mdf.setVisible(true);
    			}
    		}
    	} 	
    	
    }
    
    /**
     * Gestore delle operazioni eseguite alla pressione del tasto Modifica
     */
    @FXML
    public void modifica(){
    	
    	campotel.setText(cliente.get(2));
    	campotel.setVisible(true);
    	mdf.setVisible(false);
    	conf.setVisible(true);
    }
    
    /**
     * Gestore delle operazioni eseguite alla pressione del tasto Conferma
     */
    @FXML
    public void conferma(){
    	
    	if(campotel.getText().isEmpty())
    		v.showMessage(1, "Errore", "Riempire tutti i campi");
    	else if(campotel.getText().equals(GestioneSessione.getTelefonoCliente()) )
    		v.showMessage(0, "Informazione", "I dati inseriti sono gli stessi gi� presenti nel sistema");
    	else if(! InputController.telVerify(campotel.getText()))
    		v.showMessage(1, "Attenzione", "Formato del numero di telefono non valido");
    	else {
    		ArrayList<String> parameters = new ArrayList<String>();
    		
    		parameters.add(nome.getText());
    		parameters.add(cognome.getText());
    		parameters.add(campotel.getText());
    		parameters.add(cf.getText());
    		
    		if((boolean)fc.handleRequest("ModificaDatiCliente",parameters)){
    			v.showMessage(0, "Informazione", "Operazione completata con successo!");
    			
    			telefono.setText(campotel.getText());
    			conf.setVisible(false);
    			mdf.setVisible(false);
    			campotel.setVisible(false);
    		}
    		
    		else
    			v.showMessage(1, "Errore!", "Operazione non completata!\n Riprovare!");
    			
    		
    		
    			
    	}
    	
    	
    	
    	
    }
    /**
     * Gestore delle operazioni eseguite alla pressione del tasto Indietro
     */
    @FXML
    public void indietro(){
    	fc.handleRequest("MenuOperatore");
    }

}
