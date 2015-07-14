package presentation;


import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
/**
 * Classe che si occupa della gestione delle interfacce e della 
 * visualizzazione delle dialog di errore,informative e di conferma
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */

public class ViewDispatcher  {
	/**
	 * Dialog da visualizzare
	 */
	Alert alert;
	/**
	 * Parametro di ridimensionamento delle dimensioni delle dialog
	 */
	boolean size = false;
	
	/**
	 * Costruttore vuoto
	 */
	public ViewDispatcher(){
		
	}
	
	/**
	 * Costruttore per l'opzione di ridimensionamento
	 * @param b 
	 */
	public ViewDispatcher(boolean b){
		size = true;
	}
	/**
	 * Metodo per lo switch di interfacce
	 * @param fxml Stringa con il nome del file fxml da caricare
	 */
	public void setInterface(String fxml){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ui/"+fxml));
			GestioneSessione.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per la visualizzazione di dialogs
	 * @param code Tipo di dialog(3-conferma,2-errore,1-informativa)
	 * @param title Titolo della dialog
	 * @param message Messaggio visualizzato nella dialog
	 * @return Oggetto di tipo OptionalButton<ButtonType> per la gestione delle azioni
	 * 			dell'utente sulla dialog
	 */
	public Optional<ButtonType> showMessage(int code, String title, String message){
		Alert alert = new Alert(null);
		if(size)
			alert.getDialogPane().setPrefSize(420, 250);
		
		switch(code){
		case 0:
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle(title);
			alert.setContentText(message);
			alert.setHeaderText(null);
			alert.showAndWait();
		break;
		
		case 1:
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle(title);
			alert.setContentText(message);
			alert.setHeaderText(null);
			alert.showAndWait();
		break;
		
		case 2:
			alert.setAlertType(AlertType.CONFIRMATION);
			alert.setTitle(title);
			alert.setContentText(message);
			alert.setHeaderText(null);
			return alert.showAndWait();
		}
		return null;
	}

}
