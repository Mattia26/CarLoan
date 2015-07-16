package business;

/**
 * Classe che estende Exception, indicando un'eccezione che si genera nel caso di oggetti
 * non ritrovati nel sistema.
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public class ObjectNotFoundException extends Exception {
	ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException() {
		
	}
}
