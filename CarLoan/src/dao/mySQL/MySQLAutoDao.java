package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import dao.AutoDao;
import dao.MySQLDaoFactory;
import entity.Auto;

public class MySQLAutoDao implements AutoDao{

	@Override
	public boolean inserisciAuto(String nome, String targa, char fascia,
			double km) {
		// TODO Auto-generated method stub
		String queryInserimento;
		boolean inserito;
		queryInserimento = "insert into cars(nome, targa, fascia, ultimo_km, disponibile, "
				+ "in_manutenzione, data_manutenzione_ordinaria) values(?, ?, ?, ?, ?, ?, ?);";
		
		boolean disponibile=true;
		boolean inManutenzione=false;
		String dataManutenzioneOrdinaria;
		Calendar cal = Calendar.getInstance();
		Integer day = cal.get(Calendar.DATE);
		Integer month = cal.get(Calendar.MONTH) + 1;
		Integer year = cal.get(Calendar.YEAR) + 1;
		dataManutenzioneOrdinaria= year.toString() + "-" + month.toString() 
				+ "-" + day.toString() + ";";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryInserimento);
			statement.setString(1,nome);
			statement.setString(2,targa);
			statement.setString(3, ((Character)fascia).toString());
			statement.setDouble(4,km);
			statement.setBoolean(5,disponibile);
			statement.setBoolean(6,inManutenzione);
			statement.setString(7, dataManutenzioneOrdinaria);
			System.out.println(statement);
			
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
	public boolean modificaAuto(String targa, boolean disponibile, boolean inManutenzione, 
			String dataManutenzioneOrdinaria, double km) {
		boolean modificato;
		String queryModifica;
		queryModifica = "update cars set ultimo_km= ? , disponibile= ? , in_manutenzione= ? "
				+ ", data_manutenzione_ordinaria= ? where targa= ?;";
		// TODO Auto-generated method stub
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			statement.setDouble(1, km);
			statement.setBoolean(2, disponibile);
			statement.setBoolean(3, inManutenzione);
			statement.setString(4, dataManutenzioneOrdinaria);
			statement.setString(5, targa);
			try {
				if(statement.executeUpdate()==1)
					modificato=true;
				else
					modificato=false;
			}
			catch (SQLException e) {
				System.out.println("impossibile effettuare la query");
				modificato=false;
			}
			statement.close();
		}
		catch (SQLException e) {
			System.out.println("impossibile stabilire la connessione con il database");
			modificato=false;
		}
		return modificato;
	}

	@Override
	public boolean rimuoviAuto(String targa) {
		// TODO Auto-generated method stub
		boolean cancellata;
		String queryRimozione;
		queryRimozione= "delete from cars where targa= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryRimozione);
			statement.setString(1, targa);
			try {
				if(statement.executeUpdate()==1)
					cancellata=true;
				else
					cancellata=false;
			}
			catch(SQLException e) {
				System.out.println("impossibile effettuare la query");
				cancellata=false;
			}
			
			statement.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			cancellata = false;
		}
		return cancellata;
	}

	@Override
	public ArrayList<Auto> getAutoDisponibili() {
		// TODO Auto-generated method stub
		ArrayList<Auto> result;
		String queryAuto = "select * from cars where disponibile=true;";
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) {
				try{
					ResultSet rs=statement.executeQuery(queryAuto);
					result = new ArrayList<Auto>();
					while(rs.next()) {
						
						Auto currentTuple = new Auto(rs.getString("nome"),rs.getString("targa"),
												 rs.getString("fascia").charAt(0),rs.getBoolean("in_manutenzione"),
												 rs.getString("data_manutenzione_ordinaria"), rs.getDouble("ultimo_km"));
						
						result.add(currentTuple);
					}
					rs.close();
					statement.close();
					while(!result.isEmpty()){
						System.out.println(result.get(0).toString());
						result.remove(0);
					}
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

	@Override
	public ArrayList<Auto> getAutoSistema() {
		// TODO Auto-generated method stub
		ArrayList<Auto> result;
		String queryAutoSistema="select * from cars;";
		Statement statement;
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			statement=conn.createStatement();
			if(statement != null) {
				try {
					ResultSet rs=statement.executeQuery(queryAutoSistema);
					result = new ArrayList<Auto>();
					while(rs.next()) {
						Auto currentTuple = new Auto(rs.getString("nome"),rs.getString("targa"),
								 rs.getString("fascia").charAt(0),rs.getBoolean("in_manutenzione"),
								 rs.getString("data_manutenzione_ordinaria"), rs.getDouble("ultimo_km"));
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
