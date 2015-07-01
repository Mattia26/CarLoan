package entity;


import java.util.HashMap;
import java.util.ArrayList;

public class Auto {
	private final String modello;
	private final String targa;
	private char fascia;
	private boolean inManutenzione;
	private String dataManutenzioneOrdinaria;
	private double ultimoChilometraggio;
	
	public Auto(String modello, String targa, char fascia, boolean inManutenzione, 
			String dataManutenzioneOrdinaria, double ultimoKm) {	
		this.modello=modello;
		this.targa=targa;
		this.fascia=fascia;
		this.inManutenzione=inManutenzione;
		this.dataManutenzioneOrdinaria=dataManutenzioneOrdinaria;
		this.ultimoChilometraggio=ultimoKm;
		
	}
	
	public Auto(ArrayList<HashMap<String,String>> a, String targa) {
		int i=0;
		while(a.get(i).get("targa") != targa && i<a.size()) {
			i++;
		}
		this.targa=targa;
		this.modello=a.get(i).get("modello");
		this.fascia=(char) (Object) a.get(i).get("fascia");
		this.inManutenzione= (boolean) ((Object) a.get(i).get("inManutenzione"));
		this.dataManutenzioneOrdinaria=a.get(i).get("dataManutenzioneOrdinaria");
		this.ultimoChilometraggio=(double) (Object) a.get(i).get("ultimoChilometraggio");
	}
	
	public void setFascia(char fascia) {
		this.fascia=fascia;
	}
	
	public void setInManutenzione(boolean inManutenzione) {
		this.inManutenzione=inManutenzione;
	}
	
	public void setUltimoChilometraggio(double ultimoKm) {
		this.ultimoChilometraggio=ultimoKm;
	}
	
	public void setDataManutenzione(String data) {
		this.dataManutenzioneOrdinaria=data;
	}
	
	public String getTarga() {
		return this.targa;
	}
	
	public String getModello() {
		return this.modello;
	}
	
	public char getFascia() {
		return this.fascia;
	}

	public String getDataManutenzione() {
		return this.dataManutenzioneOrdinaria;
	}
	
	public boolean inManutenzione() {
		return this.inManutenzione;
	}
	
	public double getUltimoChilometraggio() {
		return this.ultimoChilometraggio;
	}
	
}
