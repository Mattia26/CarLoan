package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import dao.MySQLDaoFactory;
import dao.OperatoreDao;

public class MySQLOperatoreDao implements OperatoreDao{

	@Override
	public boolean inserisciOperatore(String nome, String cognome, int età, String nickname) {
		// TODO Auto-generated method stub
		boolean inserito;
		String queryInserimento="insert into operators(nome, cognome, eta, nickname) "
				+ "values(?, ?, ?, ?);";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryInserimento);
			
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setInt(3, età);
			statement.setString(4, nickname);
			try {
				if(statement.executeUpdate()==1)	
					inserito=true;
				else
					inserito=false;
			}
			catch (SQLException e) {
				System.out.println("impossibile effettuare la query");
				inserito=false;
			}
			
			statement.close();
		}
		catch (SQLException e) {
			System.out.println("impossibile stabilire la connessione con il database");
			inserito=false;
		}
		return inserito;
	}

	@Override
	public boolean modificaDatiOperatore(String nome, String cognome, int età, String nickname) {
		// TODO Auto-generated method stub
		boolean modificato;
		String queryModifica;
		queryModifica = "update operators set nome= ?, cognome= ?, eta = ? where nickname= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setInt(3, età);
			statement.setString(4, nickname);
			if(statement.executeUpdate()==1)
				modificato=true;
			else
				modificato=false;
			statement.close();
		}
		catch (SQLException e) {
			System.out.println("impossibile stabilire la connessione con il database");
			modificato=false;
		}
		return modificato;
	}

	@Override
	public boolean rimuoviOperatore(String nickname) {
		// TODO Auto-generated method stub
		boolean rimosso;
		String queryRimozione = "delete from operators where nickname= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryRimozione);
			
			statement.setString(1, nickname);
			if(statement.executeUpdate()==1)
				rimosso=true;
			else
				rimosso=false;
			statement.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			rimosso = false;
		}
		return rimosso;
	}

	@Override
	public ArrayList<HashMap<String, String>> getOperatori() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result;
		String operatori;
		operatori = "select * from operators;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) {
				try {
					ResultSet rs=statement.executeQuery(operatori);
					result = new ArrayList<HashMap<String,String>>();
					while(rs.next()) {
						HashMap<String,String> currentTuple = new HashMap<String,String>();
						currentTuple.put("Nickname: ", rs.getString("nickname"));
						currentTuple.put("Nome: ", rs.getString("nome"));
						currentTuple.put("Cognome: ",rs.getString("cognome"));
						currentTuple.put("Et�: ", rs.getString("eta"));
						result.add(currentTuple);
					}
					rs.close();
					statement.close();
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("impossibile eseguire correttamente la query");
					return null;
				}
			}
			else
				return null;
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("impossibile stabilire la connessione con il db");
				return null;
		}
	}
}
