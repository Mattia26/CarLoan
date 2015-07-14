package business;

/**
 * Classe che estende Exception, indicando un'eccezione che si genera nel caso di oggetti
 * non ritrovati nel sistema.
 * @author Honestus
 *
 */
public class ObjectNotFoundException extends Exception {
	ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException() {
		
	}
}
