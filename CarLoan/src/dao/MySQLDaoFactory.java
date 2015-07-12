package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.mySQL.*;

public class MySQLDaoFactory extends DaoFactory{
	private static String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	private static final String DBMS="jdbc:mysql";
	private static final String SERVER="localhost";
	private static final int PORT= 3306;
	private static final String DB="carloan";
	private static String USER="admin-carloan";
	private static String PASSWORD="adminpassword";
	private static Connection conn;

	
	public static Connection initConnection() throws DatabaseConnectionException { 
		// Inizializza la connessione.
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

@Override
public AutoDao getAutoDao() throws InstantiationException, IllegalAccessException {
	// TODO Auto-generated method stub
	return (AutoDao) createDao(MySQLAutoDao.class);
	}

@Override
public ContrattoDao getContrattoDao() throws InstantiationException,
		IllegalAccessException {
	// TODO Auto-generated method stub
	return (ContrattoDao) createDao(MySQLContrattoDao.class);
	}

@Override
public ClienteDao getClienteDao() throws InstantiationException,
		IllegalAccessException {
	// TODO Auto-generated method stub
	return (ClienteDao) createDao(MySQLClienteDao.class);
	}

@Override
public OperatoreDao getOperatoreDao() throws InstantiationException,
		IllegalAccessException {
	// TODO Auto-generated method stub
	return (OperatoreDao) createDao(MySQLOperatoreDao.class);
	}
}

