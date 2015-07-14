package presentation;

import java.util.ArrayList;
/**
 * Interfaccia di ApplicationController
 * 
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public interface ApplicationControllerI {
	/**
	 * Metodo che gestisce le richieste che non coinvolgono parametri in input, 
	 * la maggior parte delle quali sono richieste di switch di interfaccia
	 * 
	 * @param request Richiesta da eseguire
	 * @return Oggetto che rappresenta il risultato della richiesta
	 */
	public Object handleRequest(String request,ArrayList<String> parameters);
	/**
	 * Metodo che gestisce richieste che coinvolgono dei parametri in input
	 * 
	 * @param request Tipo di richiesta
	 * @param parameters Parametri della richiesta
	 * @return Object che rappresenta il risultato della richiesta
	 */
	public Object handleRequest(String request);

}
