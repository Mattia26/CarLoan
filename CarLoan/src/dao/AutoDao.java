package dao;

import java.util.ArrayList;

import dao.mySQL.DatabaseConnectionException;
import dao.mySQL.ExecuteQueryException;
import entity.Auto;

public interface AutoDao {
	public boolean inserisciAuto(String modello, String targa,String data_man_ord, char fascia, 
			double km); // throws ExecuteQueryException, DatabaseConnectionException;
	public boolean modificaAuto(String targa, String inizioManutenzioneStraordinaria,
			String dataManutenzioneOrdinaria, double km);
				//	throws ExecuteQueryException, DatabaseConnectionException;
	public boolean rimuoviAuto(String targa); 
	//		throws ExecuteQueryException, DatabaseConnectionException;;
	public ArrayList<Auto> getAutoDisponibili();
		//	throws ExecuteQueryException, DatabaseConnectionException;;
	public ArrayList<Auto> getAutoSistema();
		//	throws ExecuteQueryException, DatabaseConnectionException;
}
