package dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface OperatoreDao {
	public boolean inserisciOperatore(String nome, String cognome, int età, String nickname);
	public boolean modificaDatiOperatore(String nome, String cognome, int età, String nickname);
	public boolean rimuoviOperatore(String nickname);
	public ArrayList<HashMap<String, String>> getOperatori();
}
