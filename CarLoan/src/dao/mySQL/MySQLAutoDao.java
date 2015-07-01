package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import dao.AutoDao;
import dao.MySQLDaoFactory;
import entity.Auto;

public class MySQLAutoDao implements AutoDao{

	@Override
	public boolean inserisciAuto(String modello, String targa, char fascia,
			double km) {
		// TODO Auto-generated method stub
		String queryInserimento;
		boolean inserito;
		queryInserimento = "insert into cars(modello, targa, fascia, ultimo_km, in_manutenzione,"
				+ " data_manutenzione_ordinaria) values(?, ?, ?, ?, ?, ?, ?);";
		
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
			statement.setString(1,modello);
			statement.setString(2,targa);
			statement.setString(3, ((Character)fascia).toString());
			statement.setDouble(4,km);
			statement.setBoolean(5,inManutenzione);
			statement.setString(6, dataManutenzioneOrdinaria);
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
	public boolean modificaAuto(String targa, boolean inManutenzione, 
			String dataManutenzioneOrdinaria, double km) {
		// TODO Auto-generated method stub
		boolean modificato;
		String queryModifica = "update cars set ultimo_km= ? , in_manutenzione= ? "
				+ ", data_manutenzione_ordinaria= ? where targa= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			statement.setDouble(1, km);
			statement.setBoolean(2, inManutenzione);
			statement.setString(3, dataManutenzioneOrdinaria);
			statement.setString(4, targa);
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
		String queryRimozione = "delete from cars where targa= ?;";
		
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
		String queryAuto = "select * from cars where in_manutenzione=false;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) {
				try{
					ResultSet rs=statement.executeQuery(queryAuto);
					result = new ArrayList<Auto>();
					while(rs.next()) {
						Auto a = new Auto(rs.getString("modello"), rs.getString("targa"), 
						rs.getString("fascia").charAt(0), rs.getBoolean("in_manutenzione"),
						rs.getString("data_manutenzione_ordinaria"), rs.getDouble("ultimo_km"));

						result.add(a);
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
						Auto a = new Auto(rs.getString("modello"), rs.getString("targa"), 
						rs.getString("fascia").charAt(0), rs.getBoolean("in_manutenzione"),
						rs.getString("data_manutenzione_ordinaria"), rs.getDouble("ultimo_km"));
						result.add(a);
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
