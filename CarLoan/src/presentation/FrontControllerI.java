package presentation;

import java.util.ArrayList;
/**
 * Interfaccia di FrontController
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public interface FrontControllerI {
	/**
	 * Metodo per la gestione delle richieste senza parametri
	 * 
	 * @param request Nome della richiesta
	 * @return Object che rappresenta l'esito della richiesta
	 */
	public Object handleRequest(String request);
	/**
	 * Metodo per la gestione delle richieste con parametri
	 * 
	 * @param request Nome della richiesta
	 * @param parameters Parametri della richiesta
	 * @return Object che rappresenta l'esito della richiesta
	 */
	public Object handleRequest(String request,ArrayList<String> parameters);
	

}
