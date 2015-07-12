package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import utility.InputController;
import entity.Contratto;
import entity.Operatore;
import dao.ContrattoDao;
import dao.MySQLDaoFactory;

public class MySQLContrattoDao implements ContrattoDao{

	@Override
	public int inserisciContratto(String codFiscaleCliente, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoKm, String ditta, boolean macchinaRitirata) {
		// TODO Auto-generated method stub
		int id;
		String queryInserimento = "insert into contracts(codice_fiscale_cliente, "
				+ "targa_macchina, data_inizio, data_fine, acconto, tipologia, "
				+ "tipo_chilometraggio, sede_restituzione, macchina_ritirata) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryInserimento);
			statement.setString(1, codFiscaleCliente);
			statement.setString(2, targaMacchina);
			statement.setString(3, dataInizio);
			statement.setString(4, dataFine);
			statement.setInt(5, acconto);
			statement.setString(6, ((Character)tipologia).toString());
			statement.setString(7, ((Character)tipoKm).toString());
			statement.setString(8, ditta);
			statement.setBoolean(9, macchinaRitirata);
			try {
				if(statement.executeUpdate()==1) {
					statement.clearParameters();
					String queryId="select id from contracts where targa_macchina= ? "
							+ "&& data_inizio = ? && data_fine = ?;";
					try {
						statement=conn.prepareStatement(queryId);
						statement.setString(1, targaMacchina);
						statement.setString(2, dataInizio);
						statement.setString(3, dataFine);
						ResultSet rs=statement.executeQuery();
						rs.first();
						id = rs.getInt("id");
						rs.close();
						return id;
					}
					catch (SQLException e){
						return getId(targaMacchina, dataInizio, dataFine);
					}
				}
				else
					return -1;
			}
			catch(SQLException e) {
				System.out.println("impossibile effettuare correttamente la query");
				return -1;
			}
			finally {
				statement.close();
			}	
		}
		catch (SQLException | DatabaseConnectionException e) {
			System.out.println("impossibile stabilire la connessione con il database");
			return -1;
		}
	}
	
	
	
	@Override
	public boolean modificaContratto(int idContratto, 
		String targaMacchina, String dataInizio, String dataFine, int acconto, char tipologia, 
		char tipoChilometraggio, String ditta, boolean chiuso, boolean macchinaRit) {
		// TODO Auto-generated method stub
		boolean modificato;
		String queryModifica="update contracts set targa_macchina= ?, data_inizio= ?,"
				+ "data_fine= ?, acconto= ?, tipologia= ?, tipo_chilometraggio=?, "
				+ "sede_restituzione= ?, chiuso= ?, macchina_ritirata= ? where id= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			
			statement.setString(1, targaMacchina);
			statement.setString(2, dataInizio);
			statement.setString(3, dataFine);	
			statement.setInt(4, acconto);
			statement.setString(5, ((Character)tipologia).toString());
			statement.setString(6, ((Character)tipoChilometraggio).toString());
			statement.setString(7, ditta);
			statement.setBoolean(8, chiuso);
			statement.setBoolean(9, macchinaRit);
			statement.setInt(10, idContratto);
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
	public boolean cancellaContratto(int idContratto) {
		// TODO Auto-generated method stub
		boolean cancellato;
		String queryRimozione = "delete from contracts where id= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryRimozione);
			statement.setInt(1,idContratto);
			try {
				if(statement.executeUpdate()==1)
					cancellato=true;
				else
					cancellato=false;
			}
			catch (SQLException e) {
				System.out.println("impossibile effettuare la query");
				cancellato=false;
			}
			statement.close();
		}
		catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			cancellato = false;
		}
		return cancellato;
	}
	
	

	@Override
	public ArrayList<Contratto> getContrattiAttivi() {
		// TODO Auto-generated method stub
		ArrayList<Contratto> result;
		String contrattiAttivi;
		Calendar cal = Calendar.getInstance();
		Integer day = cal.get(Calendar.DATE);
		Integer month = cal.get(Calendar.MONTH) + 1;
		Integer year = cal.get(Calendar.YEAR);
		String data_attuale= year.toString() + "-" + month.toString() + "-" + day.toString();
		contrattiAttivi= "select * from contracts where chiuso=false "
				+ "&& data_fine >= '" + data_attuale + "';";
		

		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) 
				try{
					ResultSet rs=statement.executeQuery(contrattiAttivi);
					result = new ArrayList<Contratto>();
					while(rs.next()) {
						Contratto c = new Contratto(rs.getInt("id"), 
							rs.getString("codice_fiscale_cliente"), 
							rs.getString("targa_macchina"), 
							InputController.mySqlDateToString(rs.getString("data_inizio")), 
							InputController.mySqlDateToString(rs.getString("data_fine")), 
							rs.getInt("acconto"), rs.getString("tipologia").charAt(0), 
							rs.getString("tipo_chilometraggio").charAt(0), 
							rs.getString("sede_restituzione"), rs.getBoolean("chiuso"),
							rs.getBoolean("macchina_ritirata"));
						
						result.add(c);
					}
					rs.close();
					statement.close();
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("impossibile eseguire la query correttamente");
					return new ArrayList<Contratto>();
				}
			else
				return new ArrayList<Contratto>();
		}
		catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			return new ArrayList<Contratto>();
		}
	}

	@Override
	public ArrayList<Contratto> getContrattiSistema() {
		// TODO Auto-generated method stub
		ArrayList<Contratto> result;
		String contratti;
		contratti = "select * from contracts;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) 
				try{
					ResultSet rs=statement.executeQuery(contratti);
					result = new ArrayList<Contratto>();
					while(rs.next()) {
						Contratto c = new Contratto(rs.getInt("id"), 
								rs.getString("codice_fiscale_cliente"), 
								rs.getString("targa_macchina"), 
								InputController.mySqlDateToString(rs.getString("data_inizio")), 
								InputController.mySqlDateToString(rs.getString("data_fine")), 
								rs.getInt("acconto"), rs.getString("tipologia").charAt(0), 
								rs.getString("tipo_chilometraggio").charAt(0), 
								rs.getString("sede_restituzione"), rs.getBoolean("chiuso"),
								rs.getBoolean("macchina_ritirata"));
					
						result.add(c);
					}
					rs.close();
					statement.close();
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("impossibile eseguire la query correttamente");
					return new ArrayList<Contratto>();
				}
			else
				return new ArrayList<Contratto>();
		}
		catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			return new ArrayList<Contratto>();
		}
	}

	@Override
	public int getId(String targaMacchina, String dataInizio, String dataFine) {
		// TODO Auto-generated method stub
		String queryId = "select id from contracts where targa_macchina= ? "
				+ "&& data_inizio = ? && data_fine = ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement = conn.prepareStatement(queryId);
			if(statement != null) {
				statement.setString(1, targaMacchina);
				statement.setString(2, dataInizio);
				statement.setString(3, dataFine);
				try {
					ResultSet rs = statement.executeQuery();
					rs.first();
					int id=rs.getInt("id");
					rs.close();
					return id;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("impossibile eseguire la query correttamente");
					return -1;
				}
				finally {
					statement.close();
				}
			}
			else 
				return -1;
		}
		catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			return -1;
		}
	}
}
