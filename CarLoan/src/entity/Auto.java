package entity;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;

import utility.InputController;

public class Auto {
	/**
	 * Attributo final e private di tipo String: indica il modello dell'auto
	 */
	private final String modello;
	/**
	 * Attributo final e private di tipo String: indica la targa dell'auto
	 */
	private final String targa;
	/**
	 * Attributo private di tipo char: indica la fascia dell'auto
	 */
	private char fascia;
	/**
	 * Attributo private di tipo String:indica la data di inizio della manutenzione straordinaria
	 */
	private String dataInizioManutenzioneStraordinaria;
	/**
	 * Attributo private di tipo String: indica la data di inizio della manutenzione ordinaria
	 */
	private String dataManutenzioneOrdinaria;
	/**
	 * Attributo private di tipo double: indica l'ultimo chilometraggio dell'auto
	 */
	private double ultimoChilometraggio;
	
	/**
	 * Costruttore: istanzia un'auto a partire dai parametri in input
	 * @param modello di tipo String: indica il modello dell'auto da instanziare
	 * @param targa di tipo String: indica la targa dell'auto da instanziare
	 * @param fascia  di tipo char: indica la fascia dell'auto da instanziare
	 * @param dataManutenzioneStraordinaria di tipo String:
	 * indica la data di inizio della manutenzione straordinaria dell'auto da instanziare
	 * @param dataManutenzioneOrdinaria di tipo String: indica la data di inizio 
	 * della manutenzione ordinaria dell'auto da instanziare
	 * @param ultimoKm di tipo double: indica l'ultimo chilometraggio dell'auto da instanziare
	 */
	public Auto(String modello, String targa, char fascia, String dataManutenzioneStraordinaria, 
			String dataManutenzioneOrdinaria, double ultimoKm) {
		this.modello=modello;
		this.targa=targa;
		this.fascia=fascia;
		this.dataInizioManutenzioneStraordinaria=dataManutenzioneStraordinaria;
		this.dataManutenzioneOrdinaria=dataManutenzioneOrdinaria;
		this.ultimoChilometraggio=ultimoKm;
	}
	
	/**
	 * Avvalora l'attributo fascia legato all'auto con il char in input
	 * @param fascia di tipo char.
	 */
	public void setFascia(char fascia) {
		this.fascia=fascia;
	}
	
	/**
	 * Avvalora l'attributo dataInizioManutenzioneStraordinaria legato all'auto 
	 * con la String in input
	 * @param dataInizio di tipo String.
	 */
	public void setDataManutenzioneStraordinaria(String dataInizio) {
		dataInizioManutenzioneStraordinaria=dataInizio;
	}
	
	/**
	 * Avvalora l'attributo ultimoChilometraggio legato all'auto con il double in input
	 * @param ultimoKm di tipo double.
	 */
	public void setUltimoChilometraggio(double ultimoKm) {
		this.ultimoChilometraggio=ultimoKm;
	}
	
	/**
	 * Avvalora l'attributo dataManutenzioneOrdinaria legato all'auto 
	 * con la String in input
	 * @param dataInizio di tipo String.
	 */
	public void setDataManutenzioneOrdinaria(String dataInizio) {
		this.dataManutenzioneOrdinaria=dataInizio;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo targa legato all'auto
	 * @return String: valore della targa dell'auto.
	 */
	public String getTarga() {
		return this.targa;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo modello legato all'auto
	 * @return String: valore del modello dell'auto.
	 */
	public String getModello() {
		return this.modello;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo fascia legato all'auto
	 * @return char: valore della fascia dell'auto.
	 */
	public char getFascia() {
		return this.fascia;
	}

	/**
	 * Metodo public che restituisce il valore dell'attributo dataManutenzioneOrdinaria 
	 * legato all'auto
	 * @return String: valore della data di inizio della manutenzione ordinaria dell'auto
	 */
	public String getDataManutenzioneOrdinaria() {
		return this.dataManutenzioneOrdinaria;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo
	 * dataInizioManutenzioneStraordinaria legato all'auto
	 * @return String: valore della data di inizio della manutenzione straordinaria dell'auto
	 */
	public String getDataManutenzioneStraordinaria() {
		return this.dataInizioManutenzioneStraordinaria;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo ultimoChilometraggio 
	 * legato all'auto
	 * @return double: valore dell'ultimo chilometraggio dell'auto
	 */
	public double getUltimoChilometraggio() {
		return this.ultimoChilometraggio;
	}
	
}
