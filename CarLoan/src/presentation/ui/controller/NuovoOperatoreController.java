package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.InputController;
import utility.LoginUtility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NuovoOperatoreController {
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField cognome;
	
	@FXML
	private TextField telefono;
	
	@FXML
	private TextField nick;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private TextField indirizzo;
	
	private FrontController fc = new FrontController();
	
	private ViewDispatcher v = new ViewDispatcher();
	
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
	}
	
	@FXML
	public void conferma(){
		
		if(nome.getText().isEmpty() || cognome.getText().isEmpty() || telefono.getText().isEmpty()
				|| nick.getText().isEmpty() || indirizzo.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi");
		else{
			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add(nome.getText());
			parameters.add(cognome.getText());
			parameters.add(indirizzo.getText());
			parameters.add(telefono.getText());
			parameters.add(nick.getText());
			parameters.add(password.getText());
			
			if(!InputController.telVerify(telefono.getText()))
				v.showMessage(1, "Errore", "Numero di telefono inesistente");
			
			else if((boolean)fc.handleRequest("NuovoOperatore",parameters)){
				v.showMessage(0, "Informazione", "Operazione completata con successo");
				LoginUtility l = new LoginUtility();
				l.insertUser("operatore" + nick.getText(), password.getText());
				fc.handleRequest("MenuAmministratore");
			}
			else
				v.showMessage(1, "Errore", "L'operazione non è stata completata");
			
		}
	}

}