package dao;

/**
 * Classe astratta per la comunicazione con il livello Dao. Permette l'istanziazione di una
 * sottoclasse per la comunicazione con il relativo tipo di database(es. MySQLDaoFactory).
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public abstract class DaoFactory {
	/**
	 * Attributo di classe, final, di tipo intero per istanziare un DaoFactory di MYSQL.
	 */
	
	public static final int MySQL=1;
	
	/**
	 * Metodo astratto da implementare nelle sottoclassi di DaoFactory 
	 * per ottenere un'istanza di AutoDao
	 * @return AutoDao 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	
	public abstract AutoDao getAutoDao() throws InstantiationException, IllegalAccessException;
	/**
	 * Metodo astratto da implementare nelle sottoclassi di DaoFactory 
	 * per ottenere un'istanza di ContrattoDao
	 * @return Contratto 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract ContrattoDao getContrattoDao() throws InstantiationException, IllegalAccessException;
	
	/**
	 * Metodo astratto da implementare nelle sottoclassi di DaoFactory 
	 * per ottenere un'istanza di ClienteDao
	 * @return ClienteDao 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract ClienteDao getClienteDao() throws InstantiationException, IllegalAccessException;
	
	
	/**
	 * Metodo astratto da implementare nelle sottoclassi di DaoFactory 
	 * per ottenere un'istanza di DittaDao
	 * @return DittaDao 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract DittaDao getDittaDao() throws InstantiationException, IllegalAccessException;
		// TODO Auto-generated method stub
		
	
	
	/**
	 * Metodo astratto da implementare nelle sottoclassi di DaoFactory 
	 * per ottenere un'istanza di OperatoreDao
	 * @return OperatoreDao 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract OperatoreDao getOperatoreDao() throws InstantiationException, IllegalAccessException;

	/**
	 * Metodo di classe che restituisce un DaoFactory in base all'intero in input. 
	 * @param i intero indicante il tipo di Dao che verrà istanziato.
	 * @return DaoFactory 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
public static DaoFactory getDaoFactory(int i) {
	DaoFactory dao;
	switch (i) {
	case MySQL:
		dao = new MySQLDaoFactory();
		break;
	default:
		dao = null;
		break;
		}
	return dao;
	}

/**
 * Metodo di classe, visibile all'esterno solo alle sottoclassi, che restituisce un'istanza 
 * della classe c in input. 
 * @param c di tipo Class. Indica il tipo di Object che verrà istanziato.
 * @return Object : Object o sottoclasse di Object di classe Class in input.
 * @throws InstantiationException
 * @throws IllegalAccessException
 */

protected static Object createDao(Class c) 
		throws InstantiationException, IllegalAccessException {
return c.newInstance();
	}

}