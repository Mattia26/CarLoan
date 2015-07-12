package dao;

import java.util.ArrayList;
import entity.Operatore;

public interface OperatoreDao {
	public boolean inserisciOperatore(String nome, String cognome, String indirizzo, 
			String numTelefono, String nickname);
	public boolean modificaDatiOperatore(String nome, String cognome, String indirizzo, 
			String numTelefono, String nickname);
	public boolean rimuoviOperatore(String nickname);
	public ArrayList<Operatore> getOperatori();
}
