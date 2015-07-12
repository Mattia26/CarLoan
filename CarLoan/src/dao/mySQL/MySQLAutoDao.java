package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import utility.InputController;
import dao.AutoDao;
import dao.MySQLDaoFactory;
import entity.Auto;
import entity.Contratto;

public class MySQLAutoDao implements AutoDao{

	@Override
	public boolean inserisciAuto(String modello, String targa, char fascia,
			double km) {
		// TODO Auto-generated method stub
		String queryInserimento;
		boolean inserito;
		queryInserimento = "insert into cars(modello, targa, fascia, ultimo_km, "
				+ " data_manutenzione_ordinaria) values(?, ?, ?, ?, ?, ?, ?);";
		
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
			statement.setString(5, dataManutenzioneOrdinaria);
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
		catch (SQLException | DatabaseConnectionException e) {
			System.out.println("impossibile stabilire la connessione con il database");
			inserito=false;
		}
	return inserito;
	}

	
	
	@Override
	public boolean modificaAuto(String targa, String inizioManutenzioneStraordinaria, 
			String dataManutenzioneOrdinaria, double km) {
		// TODO Auto-generated method stub
		boolean modificato=false;
		
		if(inizioManutenzioneStraordinaria.equals(""))
			inizioManutenzioneStraordinaria = null;
		
			
		String queryModifica = "update cars set ultimo_km= ? , "
			+ "data_man_strao= ? "
			+ ", data_manutenzione_ordinaria= ? where targa= ?;";
			
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			statement.setDouble(1, km);
			statement.setString(2, inizioManutenzioneStraordinaria);
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
		catch (SQLException | DatabaseConnectionException e) {
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
		catch (SQLException | DatabaseConnectionException e) {
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
		String queryAuto = "select * from cars where data_inizio_manutenzione_straordinaria is"
				+ "null;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) {
				try{
					ResultSet rs=statement.executeQuery(queryAuto);
					result = new ArrayList<Auto>();
					String dataManStr;
					
					while(rs.next()) {
						try {
							dataManStr = rs.getString("data_man_strao");
						}
						catch (SQLException e) {
							dataManStr = "2000-01-01";
						}
						Auto a = new Auto(rs.getString("modello"), rs.getString("targa"), 
						rs.getString("fascia").charAt(0), 
						InputController.mySqlDateToString(dataManStr),
						InputController.mySqlDateToString(
							rs.getString("data_manutenzione_ordinaria")), 
						rs.getDouble("ultimo_km"));

						result.add(a);
					}
					rs.close();
					statement.close();
					
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("impossibile eseguire correttamente la query");
					return new ArrayList<Auto>();
				}
			}
			else
				return new ArrayList<Auto>();
		}
		catch (SQLException | DatabaseConnectionException e) {
				// TODO Auto-generated catch block
				System.out.println("impossibile stabilire la connessione con il db");
				return new ArrayList<Auto>();
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
					String dataManStr;
					
					
					while(rs.next()) {
						try {
							dataManStr = 
									rs.getString("data_man_strao");
						}
						catch (SQLException e) {
							dataManStr = "2000-01-01";
						}
						Auto a = new Auto(rs.getString("modello"), rs.getString("targa"), 
						rs.getString("fascia").charAt(0), 
						InputController.mySqlDateToString(dataManStr),
						InputController.mySqlDateToString(rs.getString("data_manutenzione_ordinaria")),
						rs.getDouble("ultimo_km"));
						result.add(a);
					}	
					rs.close();
					statement.close();
					
					return result;
				} 
				catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("impossibile eseguire correttamente la query");
				return new ArrayList<Auto>();
				}
			}
			else
				return new ArrayList<Auto>();
		}
		catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			return new ArrayList<Auto>();
		}
	}
}
