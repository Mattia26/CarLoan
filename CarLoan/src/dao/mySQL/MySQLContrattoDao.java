package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import dao.ContrattoDao;
import dao.MySQLDaoFactory;
import entity.Contratto;

public class MySQLContrattoDao implements ContrattoDao{

	@Override
	public int inserisciContratto(String codFiscaleCliente, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoKm, int idDitta) {
		// TODO Auto-generated method stub
		int id;
		String queryInserimento = "insert into contracts(codice_fiscale_cliente, "
				+ "targa_macchina, data_inizio, data_fine, acconto, tipologia, "
				+ "tipo_chilometraggio, sede_restituzione) values(?, ?, ?, ?, ?, ?, ?, ?);";
		
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
			statement.setInt(8, idDitta);
			System.out.println(statement);
			if(statement.executeUpdate()==1) {
				statement.clearParameters();
				String queryId="select id from contracts where targa_macchina= ? "
						+ "&& data_inizio = ? && data_fine = ?;";
				try {
					statement=conn.prepareStatement(queryId);
					statement.setString(1, targaMacchina);
					statement.setString(2, dataInizio);
					statement.setString(3, dataFine);
					System.out.println(statement);
					ResultSet rs=statement.executeQuery();
					rs.first();
					id = rs.getInt("id");
					rs.close();
					return id;
				}
				catch (SQLException e){
					return getId(targaMacchina, dataInizio, dataFine);
				}
				finally {
					statement.close();
				}
			}
			else
				return -1;
		}
		catch (SQLException e) {
			System.out.println("impossibile stabilire la connessione con il database");
			return -1;
		}
	}
	
	@Override
	public boolean modificaContratto(int idContratto, 
			String targaMacchina, String dataInizio, String dataFine, int acconto, 
			char tipologia, char tipoChilometraggio, int idDitta) {
		// TODO Auto-generated method stub
		boolean modificato;
		String queryModifica="update contracts set targa_macchina= ?, data_inizio= ?,"
				+ "data_fine= ?, acconto= ?, tipologia= ?, tipo_chilometraggio=?, "
				+ "sede_restituzione= ? where id= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryModifica);
			statement.setString(1, targaMacchina);
			statement.setString(2, dataInizio);
			statement.setString(3, dataFine);
			statement.setInt(4, acconto);
			statement.setString(5, ((Character)tipologia).toString());
			statement.setString(6, ((Character)tipoChilometraggio).toString());
			statement.setInt(7, idDitta);
			statement.setInt(8, idContratto);
			System.out.println(statement);
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
	public boolean cancellaContratto(int idContratto) {
		// TODO Auto-generated method stub
		boolean cancellato;
		String queryRimozione = "delete from contracts where id= ?;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(queryRimozione);
			statement.setInt(1,idContratto);
			if(statement.executeUpdate()==1)
				cancellato=true;
			else
				cancellato=false;
			statement.close();
			} 
		catch (SQLException e) {
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
		String contrattiAttivi= "select * from contracts where chiuso=false";
		

		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) 
				try{
					ResultSet rs=statement.executeQuery(contrattiAttivi);
					result = new ArrayList<Contratto>();
					while(rs.next()) {
						Contratto c;
						c = new Contratto(rs.getString("codice_fiscale_cliente"), 
						rs.getString("targa_macchina"), rs.getString("data_inizio"), 
						rs.getString("data_fine"), rs.getInt("acconto"),
						rs.getString("tipologia").charAt(0), rs.getString("tipo_chilometraggio").charAt(0), 
						rs.getString("sede_restituzione"));
						
						result.add(c);
					}
					rs.close();
					statement.close();
					
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("impossibile eseguire la query correttamente");
					return null;
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
	public ArrayList<HashMap<String, String>> getContrattiSistema() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result;
		String contratti;
		contratti = "select * from contracts;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) 
				try{
					ResultSet rs=statement.executeQuery(contratti);
					result = new ArrayList<HashMap<String,String>>();
					while(rs.next()) {
						System.out.println(rs.getString("id"));
						HashMap<String,String> currentTuple = new HashMap<String,String>();
						currentTuple.put("Id: ", (rs.getString("id")));
						currentTuple.put("Cod.Fiscale: ", rs.getString("codice_fiscale_cliente"));
						currentTuple.put("N° di targa: ",rs.getString("targa_macchina"));
						currentTuple.put("Data inizio contratto: ", rs.getString("data_inizio"));
						currentTuple.put("Data fine contratto: ", rs.getString("data_fine"));
						currentTuple.put("Acconto versato: ", rs.getString("acconto"));
						currentTuple.put("Tipologia noleggio: ",rs.getString("tipologia"));
						currentTuple.put("Tipo km: ", rs.getString("tipo_chilometraggio"));
						currentTuple.put("Sede restituzione", rs.getString("sede_restituzione"));
						result.add(currentTuple);
					}
					rs.close();
					statement.close();
					return result;
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("impossibile eseguire la query correttamente");
					return null;
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
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("impossibile stabilire la connessione con il db");
			return -1;
		}
	}
}
