package presentation.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentation.FrontController;
import presentation.ViewDispatcher;
import utility.InputController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * Classe di controllo per l'interfaccia Login
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class LoginController {
	
	@FXML
	private TextField UsernameField;
	
	@FXML
	private PasswordField Password;
	
	/**
	 * Gestore delle operazioni alla pressione del tasto Login
	 * @param e
	 */
	@FXML
	protected void Accedi(ActionEvent e){
			if(!InputController.usernameVerify(UsernameField.getText())) {
			ViewDispatcher v = new ViewDispatcher();
			v.showMessage(1, "Attenzione", "Nickname troppo corto o troppo lungo.\n "
					+ "Deve contenere da 4 a 20 caratteri!");
			}
			else if(!InputController.passwordVerify(Password.getText())) {
				ViewDispatcher v = new ViewDispatcher();
				v.showMessage(1, "Attenzione", "Password troppo corta o troppo lunga\n"
						+ "Deve contenere da 5 a 20 caratteri!");
				}
			else {
				FrontController fc = new FrontController();
				ArrayList<String> parameters = new ArrayList<String>();
				parameters.add(UsernameField.getText());
				parameters.add(Password.getText());
				fc.handleRequest("login", parameters);
			}
				
	}
}
