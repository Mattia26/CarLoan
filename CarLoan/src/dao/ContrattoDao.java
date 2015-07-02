package dao;

import java.util.ArrayList;
import entity.Contratto;

public interface ContrattoDao {
	public int inserisciContratto(String codFiscaleCliente, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoChilometraggio, String ditta, boolean macchinaRitirata);
	public boolean modificaContratto(int idContratto, String targaMacchina, 
			String dataInizio, String dataFine, int acconto, char tipologia, 
			char tipoChilometraggio, String ditta, boolean chiuso, boolean macchinaRitirata);
	public boolean cancellaContratto(int idContratto);
	public ArrayList<Contratto> getContrattiAttivi();
	public ArrayList<Contratto> getContrattiSistema();
	public int getId(String targaMacchina, String dataInizio, String dataFine);

}
