package business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Interfaccia per l'identificazione del servizio richiesto da livello di command a livello
 * di business.
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public interface LookupI {
	
	/**
 * Metodo che identifica il metodo del livello di business in base a nome della classe, 
 * nome del metodo e input atteso.
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
	public Method FindService() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException ;

}
