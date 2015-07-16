package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.mySQL.*;

/**
 * Classe per le operazioni di comunicazione con il database mySQL.
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public class MySQLDaoFactory extends DaoFactory{
	/**
	 * Attributo di classe di tipo String indicante il driver di mySQL.
	 */
	private static String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	/**
	 * Attributo di classe, final e private, di tipo String indicante il DBMS di mySQL.
	 */
	private static final String DBMS="jdbc:mysql";
	/**
	 * Attributo di classe, final e private di tipo String, 
	 * indicante il server di MySQL a cui connettersi per stabilire la connessione con mySQL.
	 */
	private static final String SERVER="localhost";
	/**
	 * Attributo di classe, final e private di tipo String,
	 * indicante il numero di porta a cui connettersi per stabilire la connessione con mySQL.
	 */
	private static final int PORT= 3306;
	/**
	 * Attributo di classe, private e final di tipo String, 
	 * indicante il nome del database di MySQL a cui connettersi.
	 */
	private static final String DB="carloan";
	/**
	 * Attributo di classe, final e private di tipo String,
	 *  indicante l'user che si utilizzerà per la connessione al database.
	 */
	private static String USER="admin-carloan";
	/**
	 * Attributo di classe, final e private di tipo String,
	 * la password con cui connettersi al database con il relativo username.
	 */
	private static String PASSWORD="adminpassword";
	/**
	 * Attributo di classe, final e private di tipo Connection, inizialmente non inizializzato.
	 */
	private static Connection conn;

	/**
	 * Metodo di classe per inizializzare una connessione tramite: nome driver, dbms,
	 * indirizzo del server mySQL e relativo numero di porta, database a cui connettersi,
	 * username e password con cui effettuare la connessione.
	 * Se la connessione è già stata avviata, restituisce la connessione attuale e non ne 
	 * inizializza una nuova.
	 * @return java.sql.Connection
	 * @throws DatabaseConnectionException
	 */
	public static Connection initConnection() throws DatabaseConnectionException { 
		// Inizializza la connessione.
		if(conn!=null && conn instanceof com.mysql.jdbc.Connection) {
			return conn;
		}
		
		try { // Caricamento del driver: possibile eccezione di tipo ClassNotFound
		Class.forName(DRIVER_CLASS_NAME);
		}
		catch(ClassNotFoundException e){
			throw new DatabaseConnectionException();
		}
		try { // Tentativo di inizializzare la connessione: possibile eccezione sqlException
			conn = DriverManager.getConnection((DBMS+"://"+SERVER+":"+PORT+"/"+DB), USER, PASSWORD);
		} 
		catch (SQLException e) {
			throw new DatabaseConnectionException();
		}
		return conn;
		
	}
	
	/**
	 * Metodo di classe che chiude la connessione con mySQL se essa 
	 * è stata precedentemente aperta.
	 */
	public static void closeConnection(){
		// Chiude la connessione 
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

/**
 * Implementa il metodo astratto getAutoDao() della classe DaoFactory.
 * @return un'istanza di MySQLAutoDao.
 */
@Override
public AutoDao getAutoDao() throws InstantiationException, IllegalAccessException {
	// TODO Auto-generated method stub
	return (AutoDao) createDao(MySQLAutoDao.class);
	}

/**
 * Implementa il metodo astratto getContrattoDao() della classe DaoFactory.
 * @return un'istanza di MySQLContrattoDao.
 */
@Override
public ContrattoDao getContrattoDao() throws InstantiationException,
		IllegalAccessException {
	// TODO Auto-generated method stub
	return (ContrattoDao) createDao(MySQLContrattoDao.class);
	}

/**
 * Implementa il metodo astratto getClienteDao() della classe DaoFactory.
 * @return un'istanza di MySQLClienteoDao.
 */
@Override
public ClienteDao getClienteDao() throws InstantiationException,
		IllegalAccessException {
	// TODO Auto-generated method stub
	return (ClienteDao) createDao(MySQLClienteDao.class);
	}

/**
 * Implementa il metodo astratto getDittaDao() della classe DaoFactory.
 * @return un'istanza di MySQLDittaDao.
 */
@Override
public DittaDao getDittaDao() throws InstantiationException,
		IllegalAccessException {
	// TODO Auto-generated method stub
	return (DittaDao) createDao(MySQLDittaDao.class);
}

/**
 * Implementa il metodo astratto getOperatoreDao() della classe DaoFactory.
 * @return un'istanza di MySQLOperatoreDao.
 */
@Override
public OperatoreDao getOperatoreDao() throws InstantiationException,
		IllegalAccessException {
	// TODO Auto-generated method stub
	return (OperatoreDao) createDao(MySQLOperatoreDao.class);
	}


}

