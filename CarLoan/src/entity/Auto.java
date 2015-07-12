package entity;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;

import utility.InputController;

public class Auto {
	private final String modello;
	private final String targa;
	private char fascia;
	private String dataInizioManutenzioneStraordinaria;
	private String dataManutenzioneOrdinaria;
	private double ultimoChilometraggio;
	
	
	public Auto(String modello, String targa, char fascia, String dataManutenzioneStraordinaria, 
			String dataManutenzioneOrdinaria, double ultimoKm) {
		this.modello=modello;
		this.targa=targa;
		this.fascia=fascia;
		this.dataInizioManutenzioneStraordinaria=dataManutenzioneStraordinaria;
		this.dataManutenzioneOrdinaria=dataManutenzioneOrdinaria;
		this.ultimoChilometraggio=ultimoKm;
	}
	
	
	public void setFascia(char fascia) {
		this.fascia=fascia;
	}
	
	public void setDataManutenzioneStraordinaria(String dataInizio) {
		dataInizioManutenzioneStraordinaria=dataInizio;
	}
	
	public void setUltimoChilometraggio(double ultimoKm) {
		this.ultimoChilometraggio=ultimoKm;
	}
	
	public void setDataManutenzioneOrdinaria(String dataInizio) {
		this.dataManutenzioneOrdinaria=dataInizio;
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

	public String getDataManutenzioneOrdinaria() {
		return this.dataManutenzioneOrdinaria;
	}
	
	public String getDataManutenzioneStraordinaria() {
		return this.dataInizioManutenzioneStraordinaria;
	}
	
	public String inManutenzioneStraordinaria() {
		return this.dataInizioManutenzioneStraordinaria;
	}
	
	public double getUltimoChilometraggio() {
		return this.ultimoChilometraggio;
	}
	
}
