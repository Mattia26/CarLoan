package entity;

import java.util.HashMap;
import java.util.ArrayList;

public class Auto {
	private final String nome;
	private final String targa;
	private char fascia;
	private boolean inManutenzione;
	private String dataManutenzioneOrdinaria;
	private double ultimoChilometraggio;

	
	public Auto(String nome, String targa, char fascia, boolean inManutenzione, 
			String dataManutenzioneOrdinaria, double ultimoKm) {	

		this.nome=nome;
		this.targa=targa;
		this.fascia=fascia;
		this.inManutenzione=inManutenzione;
		this.dataManutenzioneOrdinaria=dataManutenzioneOrdinaria;
		this.ultimoChilometraggio=ultimoKm;
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
	
	public String getNome() {
		return this.nome;
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
