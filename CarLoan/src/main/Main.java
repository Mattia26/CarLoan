package main;


import java.io.IOException;


import presentation.GestioneSessione;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;



import javafx.stage.Stage;

	
public class Main extends Application{

	public static void main(String[] args){
		
		launch(args);
		
	}
	public void start(Stage stage) throws IOException{
		
		

		Parent root = FXMLLoader.load(getClass().getResource
				("/presentation/ui/Avvio.fxml"));
		
		Scene sc = new Scene(root,600,400);
		GestioneSessione.setScene(sc);
		//http://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#textfield
		sc.getStylesheets().add(
				getClass().getResource("Style.css").toExternalForm());
        
        stage.setTitle("CarLoan Software");
        stage.setScene(sc);
        stage.setResizable(false);
        stage.show();
		
		
		
	}
}
