package dao;

import java.util.ArrayList;

import dao.mySQL.DatabaseConnectionException;
import dao.mySQL.ExecuteQueryException;
import entity.Cliente;

public interface ClienteDao {
	public boolean inserisciCliente(String nome, String cognome, String numTel, 
			String codFiscale); // throws ExecuteQueryException, DatabaseConnectionException;
	public boolean modificaCliente(String codFiscale, String numTel, String nome, 
			String cognome); // throws ExecuteQueryException, DatabaseConnectionException;
	public boolean rimuoviCliente(String codFiscale);
//			throws ExecuteQueryException, DatabaseConnectionException;
	public ArrayList<Cliente> getClienti();
	//		throws ExecuteQueryException, DatabaseConnectionException;
}
