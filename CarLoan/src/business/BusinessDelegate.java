package business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Classe per il ritrovamento del metodo richiesto da livello di presentation e conseguente
 * invio della richiesta a livello di business.
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public class BusinessDelegate {
	
	private LookupI look;
	/**
	 * Metodo per la gestione della richiesta proveniente da livello di business.
	 * Viene identificato il metodo da richiamare a livello di business, con la relativa classe
	 * ed i relativi input. Quindi viene invocato tale metodo.
	 * @param request: richiesta dal livello di presentation
	 * @param parameters: parametri inviati da livello di presentation, utili per l'esecuzione
	 * della richiesta a livello di business
	 * @return Object: risultato dell'esecuzione del metodo richiamato
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Object handleRequest(String request,Object parameters) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, InstantiationException, 
			NoSuchMethodException, SecurityException {
		
		look=new Lookup(request);
		Method m=look.FindService();
		return m.invoke(m.getDeclaringClass().newInstance(), parameters);
	}
}
