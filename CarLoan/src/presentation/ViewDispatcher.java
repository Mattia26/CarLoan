package presentation;


import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class ViewDispatcher  {
	
	Alert alert;
	boolean size = false;
	
	public ViewDispatcher(){
		
	}
	
	public ViewDispatcher(boolean b){
		size = true;
	}
	
	public void setInterface(String fxml){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ui/"+fxml));
			GestioneSessione.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
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
