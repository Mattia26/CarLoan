package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	
	@FXML
	private TextField campocogn;
	
	@FXML
	private TextField camponom;
	
	private ArrayList<String> cliente;
	
    @FXML
    public void cerca(){
    	ArrayList<String> parameter = new ArrayList<String>();
    	
    	
    	
    	if(cf.getText().isEmpty()){
    		v.showMessage(1, "Errore", "Campo vouto!");
    	}
    	else{
    		parameter.add(cf.getText());
    		cliente = (ArrayList<String>)fc.handleRequest("CercaCliente",parameter);
    		
    		if(cliente.isEmpty()){
        		v.showMessage(1, "Errore", "Cliente non trovato, riprovare!");
        	}
    		
    		else{
    			nome.setText(cliente.get(0));
    			cognome.setText(cliente.get(1));
    			telefono.setText(cliente.get(2));
    			mdf.setVisible(true);
    		}
        	
    	}
    	
    	
    	
    }
    
    @FXML
    public void modifica(){
    	camponom.setText(cliente.get(0));
    	campocogn.setText(cliente.get(1));
    	campotel.setText(cliente.get(2));
    	
    	camponom.setVisible(true);
    	campocogn.setVisible(true);
    	campotel.setVisible(true);
    	
    	mdf.setVisible(false);
    	conf.setVisible(true);
    }
    
    @FXML
    public void conferma(){
    	
    	if(camponom.getText().isEmpty() || campocogn.getText().isEmpty() || campotel.getText().isEmpty())
    		v.showMessage(0, "Errore", "Riempire tutti i campi");
    	else{
    		ArrayList<String> parameters = new ArrayList<String>();
    		
    		parameters.add(camponom.getText());
    		parameters.add(campocogn.getText());
    		parameters.add(campotel.getText());
    		parameters.add(cf.getText());
    		
    		if((boolean)fc.handleRequest("ModificaDatiCliente",parameters)){
    			v.showMessage(0, "Informazione", "Operazione completata con successo!");
    			
    			conf.setVisible(false);
    			mdf.setVisible(false);
    			camponom.setVisible(false);
    			campocogn.setVisible(false);
    			campotel.setVisible(false);
    			
    			nome.setText("");
    			cognome.setText("");
    			telefono.setText("");
    			cf.setText("");
    		}
    		else
    			v.showMessage(1, "Errore!", "Operazione non completata!\n Riprovare!");
    		
    			
    	}
    	
    	
    	
    	
    }
    
    @FXML
    public void indietro(){
    	fc.handleRequest("MenuOperatore");
    }

}
