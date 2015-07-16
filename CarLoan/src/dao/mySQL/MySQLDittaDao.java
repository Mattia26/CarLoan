package dao.mySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DittaDao;
import dao.MySQLDaoFactory;
import entity.Ditta;

/**
 * Classe per l'invio di richieste al database mySQL per le operazioni sulle ditte
 * @author Giuseppe Onesto
 * @author Mattia Menna
 */
public class MySQLDittaDao implements DittaDao{
	
	@Override
	public ArrayList<Ditta> getDitte() {
	ArrayList<Ditta> result;
	String ditteSistema;
	ditteSistema= "select * from sites;";
	
	try {
		Connection conn=MySQLDaoFactory.initConnection();
		Statement statement=conn.createStatement();
		if(statement!=null) {
			try{
				ResultSet rs=statement.executeQuery(ditteSistema);
				result = new ArrayList<Ditta>();
				while(rs.next()) {
					Ditta c = new Ditta(rs.getString("citta"), rs.getString("indirizzo"));
					result.add(c);
				}
				rs.close();
				statement.close();
				return result;
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				return new ArrayList<Ditta>();
				//throw new ExecuteQueryException();
			}
		}
		else
			return new ArrayList<Ditta>();
	}
	catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
		return new ArrayList<Ditta>();
			//throw new DatabaseConnectionException(); 
	}
}
}
