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

	@Override
	public boolean inserisciOperatore(String nome, String cognome, String indirizzo, 
			int numTelefono, String nickname) {
		// TODO Auto-generated method stub
		boolean inserito;
		String queryInserimento="insert into operators(nome, cognome, indirizzo, "
				+ "telefono, nickname) values(?, ?, ?, ?);";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryInserimento);
			
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, indirizzo);
			statement.setInt(4, numTelefono);
			statement.setString(5, nickname);
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
	public boolean modificaDatiOperatore(String nome, String cognome, 
			String indirizzo, int numTelefono, String nickname) {
		// TODO Auto-generated method stub
		boolean modificato;
		String queryModifica;
		queryModifica = "update operators set nome= ?, cognome= ?, eta = ? where nickname= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, indirizzo);
			statement.setInt(4, numTelefono);
			statement.setString(5, nickname);
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
	public ArrayList<Operatore> getOperatori() {
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
						rs.getInt("telefono"), rs.getString("nickname"));
				
						result.add(o);
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
