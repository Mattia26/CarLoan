package presentation.command;

import java.util.ArrayList;
/**
 * Interfaccia per l'incapsulamento di un operazione con parametri multipli o singolo
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public interface Command {
	/**
	 * Metodo che esegue l'operazione richiesta e restituisce il risultato
	 * @param parameters Parametri per l'esecuzione dell'operazione
	 * @return Object rappresentante l'esito o i dati risultato della richiesta
	 */
	public Object Execute(ArrayList<String> parameters);
	/**
	 * Metodo che esegue l'operazione richiesta e restituisce il risultato
	 * @param parameter Parametro per l'esecuzione dell'operazione
	 * @return Object rappresentante l'esito o i dati risultato della richiesta
	 */
	public Object Execute(String parameter);
	
}
