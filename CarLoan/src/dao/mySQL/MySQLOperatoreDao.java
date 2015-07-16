package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dao.MySQLDaoFactory;
import dao.OperatoreDao;
import entity.Operatore;

public class MySQLOperatoreDao implements OperatoreDao{

	/**
	 * Inserisce un nuovo operatore nel database mySQL
	 */
	@Override
	public boolean inserisciOperatore(String nome, String cognome, String indirizzo, 
			String numTelefono, String nickname) { // throws DatabaseConnectionException, ExecuteQueryException {
		// TODO Auto-generated method stub
		boolean inserito;
		String queryInserimento="insert into operators(nome, cognome, indirizzo, "
				+ "telefono, nickname) values(?, ?, ?, ?, ?);";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryInserimento);
			
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, indirizzo);
			statement.setString(4, numTelefono);
			statement.setString(5, nickname);
			try {
				if(statement.executeUpdate() == 1)	
					inserito=true;
				else
					inserito=false;
			}
			catch (SQLException e) {
				inserito=false;
				//throw new ExecuteQueryException();
			}
			
			statement.close();
		}
		catch (SQLException | DatabaseConnectionException e) {
			inserito=false;
		//	throw new DatabaseConnectionException();
		}
		return inserito;
	}

	/**
	 * Modifica l' operatore identificato dal nickname in input 
	 * ed i relativi dati nel database mySQL
	 */
	@Override
	public boolean modificaDatiOperatore(String nome, String cognome, 
			String indirizzo, String numTelefono, String nickname) {
			//		throws DatabaseConnectionException, ExecuteQueryException {
		// TODO Auto-generated method stub
		boolean modificato;
		String queryModifica;
		queryModifica = "update operators set nome= ?, cognome= ?, indirizzo = ?,"
				+ "telefono = ? where nickname= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, indirizzo);
			statement.setString(4, numTelefono);
			statement.setString(5, nickname);
			try {
				if(statement.executeUpdate() == 1)	
					modificato=true;
				else
					modificato=false;
			}
			catch (SQLException e) {
				modificato=false;
			//	throw new ExecuteQueryException();
			}
			statement.close();
		}
		catch (SQLException | DatabaseConnectionException e) {
			modificato=false;
		//	throw new DatabaseConnectionException();
			
		}
		return modificato;
	}

	
	/**
	 * Rimuove l'operatore identificato dal nickname in input 
	 * ed i relativi dati nel database mySQL
	 */
	@Override
	public boolean rimuoviOperatore(String nickname) { // throws DatabaseConnectionException, ExecuteQueryException {
		// TODO Auto-generated method stub
		boolean rimosso;
		String queryRimozione = "delete from operators where nickname= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryRimozione);
			statement.setString(1, nickname);
			try {
				if(statement.executeUpdate() == 1)	
					rimosso=true;
				else
					rimosso=false;
			}
			catch (SQLException e) {
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
	 * Prende dal database mySQL tutti gli operatori presenti in esso
	 */
	@Override
	public ArrayList<Operatore> getOperatori() {// throws ExecuteQueryException, DatabaseConnectionException {
		// TODO Auto-generated method stub
		ArrayList<Operatore> result;
		String operatori;
		operatori = "select * from operators;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) {
				try {
					ResultSet rs=statement.executeQuery(operatori);
					result = new ArrayList<Operatore>();
					while(rs.next()) {
						Operatore o = new Operatore (rs.getString("nome"), 
						rs.getString("cognome"), rs.getString("indirizzo"),
						rs.getString("telefono"), rs.getString("nickname"));
				
						result.add(o);
					}
					rs.close();
					statement.close();
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					//throw new ExecuteQueryException();
					return new ArrayList<Operatore>();
				}
			}
			else
				return new ArrayList<Operatore>();
		}
		catch (SQLException | DatabaseConnectionException e) {
				// TODO Auto-generated catch block
				//throw new DatabaseConnectionException();
				return new ArrayList<Operatore>();
		}
	}
}
