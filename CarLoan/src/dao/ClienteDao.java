package dao;

import java.util.*;

public interface ClienteDao {
	public boolean inserisciCliente(String nome, String cognome, int numTel, String codFiscale);
	public boolean modificaCliente(String codFiscale, int numTel);
	public boolean rimuoviCliente(String codFiscale);
	public ArrayList<HashMap<String, String>> getClienti();
}
