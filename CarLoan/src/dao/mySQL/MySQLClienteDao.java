package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ClienteDao;
import dao.MySQLDaoFactory;
import entity.Cliente;
import entity.Contratto;

/**
 * Classe per l'invio di richieste al database mySQL per le operazioni sui clienti
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public class MySQLClienteDao implements ClienteDao {

	/**
	 * Inserisce un nuovo cliente nel database mySQL
	 */
	@Override
	public boolean inserisciCliente(String nome, String cognome, String numTel, 
			String codFiscale) {// throws ExecuteQueryException, DatabaseConnectionException{
		// TODO Auto-generated method stub
		
		boolean inserito;
		String query_inserimento = "insert into clients(nome,cognome,telefono,codice_fiscale) "
				+ "values (?, ?, ?, ?);";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(query_inserimento);
			
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, numTel);
			statement.setString(4, codFiscale);
			try {
				statement.executeUpdate();
				inserito=true;
			}
			catch (SQLException e){
				inserito=false;
				//throw new ExecuteQueryException();
			}
			
			statement.close();
		}
		catch (SQLException | DatabaseConnectionException e) {
			inserito=false;
			//throw new DatabaseConnectionException();
		}
		return inserito;
	}

	
	/**
	 * Modifica il cliente identificato dal codice fiscale in input 
	 * ed i relativi dati nel database mySQL
	 */
	@Override
	public boolean modificaCliente(String codFiscale, String numTel, String nome, 
			String cognome) { //throws ExecuteQueryException, DatabaseConnectionException {
		// TODO Auto-generated method stub
		boolean modificato;
		String query_modifica;
		query_modifica= "update clients set telefono= ?, nome= ?, cognome= ?  where codice_fiscale= ?;";			
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(query_modifica);

			statement.setString(1,numTel);
			statement.setString(2, nome);
			statement.setString(3, cognome);
			statement.setString(4, codFiscale);
			try {
				if(statement.executeUpdate()==1)
					modificato=true;
				else
					modificato=false;
			}
			catch (SQLException e){
				modificato=false;
				//throw new ExecuteQueryException();
			}
			
			statement.close();
		}
		catch (SQLException | DatabaseConnectionException e) {
			modificato=false;
			//throw new DatabaseConnectionException();	
		}
		return modificato;
	}

	
	/**
	 * Rimuove il cliente identificato dal codice fiscale in input 
	 * ed i relativi dati nel database mySQL
	 */
	@Override
	public boolean rimuoviCliente(String codFiscale) { //throws ExecuteQueryException, DatabaseConnectionException {
		// TODO Auto-generated method stub
		boolean rimosso;
		String query_rimozione;
		query_rimozione="delete from clients where codice_fiscale= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(query_rimozione);
			statement.setString(1, codFiscale);
			try {
				if(statement.executeUpdate()==1)
					rimosso=true;
				else
					rimosso=false;
			}
			catch (SQLException e){
				rimosso=false;
			//	throw new ExecuteQueryException();
			
			}
			statement.close();
		}
		catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			rimosso = false;
			//throw new DatabaseConnectionException();
			
		}
		return rimosso;
	}

	
	/**
	 * Prende dal database mySQL tutti i clienti presenti in esso
	 */
	@Override
	public ArrayList<Cliente> getClienti() {
		//	throws ExecuteQueryException, DatabaseConnectionException {
		// TODO Auto-generated method stub
		ArrayList<Cliente> result;
		String clienti_sistema;
		clienti_sistema= "select * from clients;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) {
				try{
					ResultSet rs=statement.executeQuery(clienti_sistema);
					result = new ArrayList<Cliente>();
					while(rs.next()) {
						Cliente c = new Cliente(rs.getString("nome"), rs.getString("cognome"),
								rs.getString("telefono"), rs.getString("codice_fiscale"));
					
						result.add(c);
					}
					rs.close();
					statement.close();
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					return new ArrayList<Cliente>();
					//throw new ExecuteQueryException();
				}
			}
			else
				return new ArrayList<Cliente>();
		}
		catch (SQLException | DatabaseConnectionException e) {
				// TODO Auto-generated catch block
			return new ArrayList<Cliente>();
				//throw new DatabaseConnectionException(); 
		}
	}
}
