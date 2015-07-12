package business.entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entity.ListinoPrezzi;

public class ListinoBusiness {
	private final String NAMEFILE="Listino Prezzi Carloan";
	private ListinoPrezzi l;
	
	public ListinoBusiness() {
		try {
			l = carica();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			l = new ListinoPrezzi();
		}
	}
	
	public boolean inserisciNuovoListino(ArrayList<String> parameters) {
		l.setCostoGiornaliero(Integer.parseInt(parameters.get(2)));
		l.setCostoSettimanale(Integer.parseInt(parameters.get(3)));
		l.setCostoKmLimitato(Integer.parseInt(parameters.get(1)));
		l.setCostoKmIllimitato(Integer.parseInt(parameters.get(0)));
		try {
			salva();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	
	
	public ArrayList<Integer> getPrezzi() {
		ArrayList<Integer> prezzi = new ArrayList<Integer>();
		
		prezzi.add(l.getCostoGiornaliero());
		prezzi.add(l.getCostoSettimanale());
		prezzi.add(l.getCostoKmLimitato());
		prezzi.add(l.getCostoKmIllimitato());
		
		return prezzi;		
	}
	public void salva() throws IOException { 
			FileOutputStream fileToSave = new FileOutputStream(NAMEFILE);
			ObjectOutputStream fileSaved = new ObjectOutputStream (fileToSave);
			fileSaved.writeObject(l);
			fileSaved.close();
	}
	
	
	public ListinoPrezzi carica() throws IOException, ClassNotFoundException {
		FileInputStream inputFile = new FileInputStream(NAMEFILE);
		ObjectInputStream fileToLoad = new ObjectInputStream(inputFile);
		ListinoPrezzi fileLoaded = (ListinoPrezzi) fileToLoad.readObject();
		fileToLoad.close();
		return fileLoaded;
	}
	
}
