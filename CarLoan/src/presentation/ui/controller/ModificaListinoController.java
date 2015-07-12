package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	
	@FXML
	public void conferma(){
		if(ChilometraggioI.getText().isEmpty() || ChilometraggioL.getText().isEmpty() ||
				TariffaG.getText().isEmpty() || TariffaS.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi!");
		else{
			try{
				Integer.parseInt(ChilometraggioI.getText());
				Integer.parseInt(ChilometraggioL.getText());
				Integer.parseInt(TariffaG.getText());
				Integer.parseInt(TariffaS.getText());
				
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
			}catch(NumberFormatException e){
				v.showMessage(1, "Errore", "Per favore inserire degli interi");
			}
		}
	}

	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
		
	}
}
