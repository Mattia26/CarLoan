package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia ModificaListino
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class ModificaListinoController {
	
	@FXML
	private TextField ChilometraggioI;
	
	@FXML
	private TextField ChilometraggioL;
	
	@FXML
	private TextField TariffaG;
	
	@FXML
	private TextField TariffaS;
	
	private FrontController fc = new FrontController();
	
	private ViewDispatcher v = new ViewDispatcher();
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void conferma(){
		if(ChilometraggioI.getText().isEmpty() || ChilometraggioL.getText().isEmpty() ||
				TariffaG.getText().isEmpty() || TariffaS.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi!");
		else{
			try{
				int kmI = Integer.parseInt(ChilometraggioI.getText()); 
				int kmL = Integer.parseInt(ChilometraggioL.getText());
				int prezzoG = Integer.parseInt(TariffaG.getText());
				int prezzoS = Integer.parseInt(TariffaS.getText());
				
				if(kmI > 0 && kmL > 0 && prezzoG > 0 && prezzoS > 0) {
					ArrayList<String> parameters = new ArrayList<String>();
					parameters.add(ChilometraggioI.getText());
					parameters.add(ChilometraggioL.getText());
					parameters.add(TariffaG.getText());
					parameters.add(TariffaS.getText());
				
					if((boolean)fc.handleRequest("ModificaListino",parameters)){
						v.showMessage(0, "Informazione", "Operazione completata con successo!");
						fc.handleRequest("MenuAmministratore");
					}
					else
						v.showMessage(1, "Errore", "Operazione non riuscita!");
				
				}
				else
					v.showMessage(1, "Attenzione", "Per favore inserire dei valori positivi");
			
			}catch(NumberFormatException e){
				v.showMessage(1, "Errore", "Per favore inserire degli interi");
			}
		}
	}
    /**
     * Gestore per le operazioni eseguite alla pressione del tasto Indietro
     */
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
		
	}
}
