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

public class MySQLClienteDao implements ClienteDao {

	@Override
	public boolean inserisciCliente(String nome, String cognome, String numTel, String codFiscale){
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
	public boolean modificaCliente(String codFiscale, String numTel) {
		// TODO Auto-generated method stub
		boolean modificato;
		String query_modifica;
		query_modifica= "update clients set telefono= ? where codice_fiscale= ?;";			
		
		try {
			Connection conn=MySQLDaoFactory.initConnection();
			PreparedStatement statement=conn.prepareStatement(query_modifica);

			statement.setString(1,numTel);
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
	public ArrayList<Cliente> getClienti() {
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
