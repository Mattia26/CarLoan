package presentation;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ViewDispatcher  {
	
	Alert alert;
	
	public void setInterface(String fxml){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ui/"+fxml));
			GestioneSessione.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void showMessage(int code, String title, String message){
		Alert alert = new Alert(null);//http://code.makery.ch/blog/javafx-dialogs-official/
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
			alert.showAndWait();
		}
		
	}

}
