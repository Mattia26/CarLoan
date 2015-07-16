package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.GestioneSessione;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia ModificaListino
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class ModificaListinoController implements Initializable{
	
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
					if(GestioneSessione.getCostoGiornaliero().equals(TariffaG.getText()) &&
						GestioneSessione.getCostoSettimanale().equals(TariffaS.getText()) &&
						GestioneSessione.getCostoKmLimitato().equals(ChilometraggioL.getText()) &&
						GestioneSessione.getCostoKmIllimitato().equals(ChilometraggioI.getText())) {
						v.showMessage(0, "Attenzione", "I prezzi inseriti sono gli stessi "
								+ "già registrati nel sistema");
						return;
					}
					
					
					ArrayList<String> parameters = new ArrayList<String>();
					parameters.add(ChilometraggioI.getText());
					parameters.add(ChilometraggioL.getText());
					parameters.add(TariffaG.getText());
					parameters.add(TariffaS.getText());
					
					if((boolean)fc.handleRequest("ModificaListino",parameters)){
						v.showMessage(0, "Informazione", "Operazione completata con successo!");
						GestioneSessione.setCostoGiornaliero(Integer.parseInt(TariffaG.getText()));
						GestioneSessione.setCostoSettimanale(Integer.parseInt(TariffaS.getText()));
						GestioneSessione.setCostoKmLimitato(Integer.parseInt(ChilometraggioL.getText()));
						GestioneSessione.setCostoKmIllimitato(Integer.parseInt(ChilometraggioI.getText()));

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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(GestioneSessione.getCostoGiornaliero()==null || 
				GestioneSessione.getCostoSettimanale()==null ||
				GestioneSessione.getCostoKmLimitato()==null ||
				GestioneSessione.getCostoKmIllimitato()==null) {
			
			ArrayList<Integer> prezzi = (ArrayList<Integer>)fc.handleRequest("GetPrezziAttuali");
			if(! prezzi.isEmpty()) {
				TariffaG.setText(String.valueOf(prezzi.get(0)) + "€/giorno");
				TariffaS.setText(String.valueOf(prezzi.get(1)) + "€/settimana");
				ChilometraggioL.setText(String.valueOf(prezzi.get(2)) + "€/50km");
				ChilometraggioI.setText(String.valueOf(prezzi.get(3)) + "€/km");
			}
			else
				v.showMessage(1, "Attenzione", "Purtroppo è stato impossibile ritrovare i"
						+ "prezzi attualmente in vigore");
		}
		
		else {
			TariffaG.setText(GestioneSessione.getCostoGiornaliero() + "€/giorno");
			TariffaS.setText(GestioneSessione.getCostoSettimanale() + "€/settimana");
			ChilometraggioL.setText(GestioneSessione.getCostoKmLimitato() + "€/50km");
			ChilometraggioI.setText(GestioneSessione.getCostoKmIllimitato() + "€/km");
		}
				
	}
}
