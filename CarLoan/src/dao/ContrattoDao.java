package dao;

import java.util.*;

import entity.Contratto;

public interface ContrattoDao {
	public int inserisciContratto(String codFiscaleCliente, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoChilometraggio, int idDitta);
	public boolean modificaContratto(int idContratto, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoChilometraggio, int idDitta);
	public boolean cancellaContratto(int idContratto);
	public ArrayList<Contratto> getContrattiAttivi();
	public ArrayList<HashMap<String,String>> getContrattiSistema();
	public int getId(String targaMacchina, String dataInizio, String dataFine);

}
