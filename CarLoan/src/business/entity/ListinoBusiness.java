package business.entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import entity.ListinoPrezzi;

/**
 * Classe per la gestione di richieste da livello di business a livello inferiore(in questo caso
 * per le operazioni sul file di listino) sulle operazioni relative al listino prezzi.
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public class ListinoBusiness {
	/**
	 * Attributo final e private di tipo String: indica il nome del file che conterrà
	 * il ListinoPrezzi.
	 */
	private final String NAMEFILE="Listino Prezzi Carloan";
	/**
	 * Attributo private di tipo ListinoPrezzi: indica il ListinoPrezzi contenente i prezzi
	 * per le varie tipologie di noleggio.
	 */
	private ListinoPrezzi l;
	
	/**
	 * Costruttore: prova a caricare il ListinoPrezzi l. In caso di insuccesso, crea un nuovo
	 * ListinoPrezzi.
	 */
	public ListinoBusiness() {
		try {
			l = carica();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			l = new ListinoPrezzi();
		}
	}
	
	/**
	 * Metodo che permette la modifica dei prezzi relativi alle varie tipologie di noleggio
	 * @param parameters di tipo ArrayList<String>: contiene i prezzi da inserire
	 * nel listino per le varie tipologie di noleggio. Salva quindi il ListinoPrezzi.
	 * @return true se le modifiche son avvenute correttamente ed il file è stato salvato 
	 * correttamente; false altrimenti.
	 */
	public boolean modificaPrezzi(ArrayList<String> parameters) {
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
	
	
	/**
	 * Prende i prezzi attualmente in vigore
	 * @return ListinoPrezzi attualmente salvato nel sistema o un nuovo ListinoPrezzi con i 
	 * prezzi di default, se non è salvato alcun Listino.
	 */
	public ListinoPrezzi getPrezzi(String s) {
		
		try {
			return carica();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			return new ListinoPrezzi();
		}
				
	}
	
	/**
	 * Salva il file contenente il ListinoPrezzi l e avente nome uguale al valore dell'attributo
	 * NAMEFILE. 
	 * @throws IOException
	 */
	public void salva() throws IOException { 
			FileOutputStream fileToSave = new FileOutputStream(NAMEFILE);
			ObjectOutputStream fileSaved = new ObjectOutputStream (fileToSave);
			fileSaved.writeObject(l);
			fileSaved.close();
	}
	
	
	/**
	 *Carica il ListinoPrezzi tramite file avente nome uguale al valore dell'attributo NAMEFILE. 
	 */
	public ListinoPrezzi carica() throws IOException, ClassNotFoundException {
		FileInputStream inputFile = new FileInputStream(NAMEFILE);
		ObjectInputStream fileToLoad = new ObjectInputStream(inputFile);
		ListinoPrezzi fileLoaded = (ListinoPrezzi) fileToLoad.readObject();
		fileToLoad.close();
		return fileLoaded;
	}
	
}
