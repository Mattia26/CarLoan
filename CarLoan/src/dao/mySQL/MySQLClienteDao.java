package dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import dao.ClienteDao;
import dao.MySQLDaoFactory;

public class MySQLClienteDao implements ClienteDao {

	@Override
	public boolean inserisciCliente(String nome, String cognome, int numTel, String codFiscale){
		// TODO Auto-generated method stub
		
		boolean inserito;
		String query_inserimento = "insert into clients(nome,cognome,telefono,codice_fiscale) "
				+ "values (?, ?, ?, ?);";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(query_inserimento);
			
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setInt(3, numTel);
			statement.setString(4, codFiscale);
			System.out.println(statement);
			try {
				statement.executeUpdate();
				inserito=true;
			}
			catch (SQLException e){
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
	public boolean modificaCliente(String codFiscale, int numTel) {
		// TODO Auto-generated method stub
		boolean modificato;
		String query_modifica;
		query_modifica= "update clients set telefono= ? where codice_fiscale= ?;";			
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(query_modifica);

			statement.setInt(1,numTel);
			statement.setString(2, codFiscale);
			try {
				if(statement.executeUpdate()==1)
					modificato=true;
				else
					modificato=false;
			}
			catch (SQLException e){
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
	public boolean rimuoviCliente(String codFiscale) {
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
				System.out.println("impossibile effettuare la query");
				rimosso=false;
			}
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
	public ArrayList<HashMap<String, String>> getClienti() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result;
		String clienti_sistema;
		clienti_sistema= "select * from clients;";
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			Statement statement=conn.createStatement();
			if(statement!=null) {
				try{
					ResultSet rs=statement.executeQuery(clienti_sistema);
					result = new ArrayList<HashMap<String,String>>();
					while(rs.next()) {
						HashMap<String,String> current_tuple = new HashMap<String,String>();
						current_tuple.put("Nome: ", rs.getString("nome"));
						current_tuple.put("Cognome: ", rs.getString("cognome"));
						current_tuple.put("N° di Telefono: ",rs.getString("telefono"));
						current_tuple.put("Codice Fiscale: ", rs.getString("codice_fiscale"));
						result.add(current_tuple);
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
