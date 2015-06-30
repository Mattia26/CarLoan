package dao;

import java.util.*;

import entity.Auto;

public interface AutoDao {
	public boolean inserisciAuto(String nome, String targa, char fascia, double km);
	public boolean modificaAuto(String targa, boolean disponibile, boolean inManutenzione,
			String dataManutenzioneOrdinaria, double km);
	public boolean rimuoviAuto(String targa);
	public ArrayList<Auto> getAutoDisponibili();
	public ArrayList<Auto> getAutoSistema();
}
