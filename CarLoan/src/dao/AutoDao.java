package dao;

import java.util.ArrayList;
import entity.Auto;

public interface AutoDao {
	public boolean inserisciAuto(String modello, String targa, char fascia, double km);
	public boolean modificaAuto(String targa, String inizioManutenzioneStraordinaria,
			String dataManutenzioneOrdinaria, double km);
	public boolean rimuoviAuto(String targa);
	public ArrayList<Auto> getAutoDisponibili();
	public ArrayList<Auto> getAutoSistema();
}
