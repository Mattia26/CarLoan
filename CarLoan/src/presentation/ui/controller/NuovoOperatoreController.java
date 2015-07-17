package presentation.ui.controller;

import java.util.ArrayList;

import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.InputController;
import utility.LoginUtility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia NuovoOperatore
 * @author Mattia Menna
 * @author Giuseppe Onesto
 *
 */
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
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Indietro
	 */
	@FXML
	public void indietro(){
		fc.handleRequest("MenuAmministratore");
	}
	
	/**
	 * Gestore per le operazioni eseguite alla pressione del tasto Conferma
	 */
	@FXML
	public void conferma(){
		
		if(nome.getText().isEmpty() || cognome.getText().isEmpty() || telefono.getText().isEmpty()
				|| nick.getText().isEmpty() || indirizzo.getText().isEmpty())
			v.showMessage(1, "Errore", "Riempire tutti i campi");
		else if(!InputController.nomeVerify(nome.getText()))
			v.showMessage(1, "Attenzione", "Lunghezza del nome non valida.\n"
					+ "Il nome deve essere tra 4 e 20 caratteri.");
		else if(!InputController.cognomeVerify(cognome.getText()))
			v.showMessage(1, "Attenzione", "Lunghezza del cognome non valida.\n"
					+ "Il cognome deve essere tra 4 e 30 caratteri.");
		else if(!InputController.indirizzoVerify(indirizzo.getText()))
			v.showMessage(1, "Attenzione", "Lunghezza dell'indirizzo non valida.\n"
					+ "Deve contenere tra i 10 e i 50 caratteri.");
		else if(!InputController.telVerify(telefono.getText()))
			v.showMessage(2, "Attenzione", "Formato del numero di telefono non valido");
		else if(!InputController.usernameVerify(nick.getText()))
			v.showMessage(1, "Attenzione", "Nickname troppo corto o troppo lungo.\n "
					+ "Deve contenere da 4 a 20 caratteri!");
		else if(!InputController.passwordVerify(password.getText())) {
			v.showMessage(1, "Attenzione", "Password troppo corta o troppo lunga\n"
					+ "Deve contenere da 5 a 20 caratteri!");
			}
		
		else{
			LoginUtility l = new LoginUtility();
			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add(nome.getText());
			parameters.add(cognome.getText());
			parameters.add(indirizzo.getText());
			parameters.add(telefono.getText());
			parameters.add(nick.getText());
			
			
			if(l.insertUser("operatore" + nick.getText(), password.getText())) {
				
				if((boolean)fc.handleRequest("NuovoOperatore",parameters)){
					fc.handleRequest("MenuAmministratore");
					v.showMessage(0, "Informazione", "Operazione completata con successo");
				}
				
				else {
					v.showMessage(1, "Attenzione", "Impossibile inserire correttamente "
							+ "i dati dell'operatore. Sarà comunque possibile effettuare "
							+ "l'accesso con nickname e password inseriti e cambiare i dati"
							+ "personali in seguito.");
					fc.handleRequest("MenuAmministratore");
				}
			}
			
			else
				v.showMessage(1, "Errore", "Esiste già un utente con tale nickname");
			
		}
	}

}