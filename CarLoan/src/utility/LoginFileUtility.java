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
	
	static void salva(HashMap<String,String> f) throws FileNotFoundException, IOException{
			FileOutputStream fileToSave = new FileOutputStream(NAMEFILE);
			ObjectOutputStream fileSaved = new ObjectOutputStream (fileToSave);
			fileSaved.writeObject(f);
			fileSaved.close();
		}

	static HashMap<String,String> carica()
			throws IOException,ClassNotFoundException{ 
		FileInputStream inputFile = new FileInputStream(NAMEFILE);
		ObjectInputStream fileToLoad = new ObjectInputStream(inputFile);
		HashMap<String,String> fileLoaded = (HashMap<String,String>) fileToLoad.readObject();
		fileToLoad.close();
		return fileLoaded;
	}
	
	
	
}
