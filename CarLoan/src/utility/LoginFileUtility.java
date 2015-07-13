package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class LoginFileUtility{
	static final String NAMEFILE="Login Carloan"; 
	//attributo statico e non modificabile: indica il nome del file in cui sarà salvata 
	// la map contenente username e password di operatori e amministratore
	

	
	static void salva(HashMap<String,String> f) throws FileNotFoundException, IOException{ // salva la map
			FileOutputStream fileToSave = new FileOutputStream(NAMEFILE);
			ObjectOutputStream fileSaved = new ObjectOutputStream (fileToSave);
			fileSaved.writeObject(f);
			fileSaved.close();
		}

	static HashMap<String,String> carica() // carica la map
			throws IOException,ClassNotFoundException{ 
		FileInputStream inputFile = new FileInputStream(NAMEFILE);
		ObjectInputStream fileToLoad = new ObjectInputStream(inputFile);
		HashMap<String,String> fileLoaded = (HashMap<String,String>) fileToLoad.readObject();
		fileToLoad.close();
		return fileLoaded;
	}
	
	
	
}
