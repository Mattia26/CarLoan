package entity;

import java.io.Serializable;

public class ListinoPrezzi implements Serializable{
	/**
	 * Attributo private di tipo int: indica l'importo da saldare per ogni giorno di noleggio
	 * nel caso di tipologia di noleggio giornaliera.
	 */
	private int costoGiornaliero;
	/**
	 * Attributo private di tipo int: indica l'importo da saldare per ogni settimana di noleggio
	 * nel caso di tipologia di noleggio settimanale.
	 */
	private int costoSettimanale;
	/**
	 * Attributo private di tipo int: indica l'importo da saldare per ogni chilometro
	 * di corsa nel caso di tipologia di noleggio a chilometraggio limitato.
	 */
	private int costoChilometraggioLimitato;
	/**
	 * Attributo private di tipo int: indica l'importo da saldare per ogni 50 chilometri
	 * di corsa nel caso di tipologia di noleggio a chilometraggio illimitato.
	 */
	private int costoChilometraggioIllimitato;
		
	/**
	 * Costruttore: stabilisce i prezzi di default
	 */
		public ListinoPrezzi() {
			costoGiornaliero=10;
			costoSettimanale=60;
			costoChilometraggioLimitato=40;
			costoChilometraggioIllimitato=1;
		}
		
		/** 
		 * Avvalora l'attributo costoGiornaliero legato al listino con l'int in input
		 * @param costo di tipo int
		 */
		public void setCostoGiornaliero(int costo) {
			this.costoGiornaliero=costo;
		}
		
		/** 
		 * Avvalora l'attributo costoSettimanale legato al listino con l'int in input
		 * @param costo di tipo int
		 */
		public void setCostoSettimanale(int costo) {
			this.costoSettimanale=costo;
		}
		
		/** 
		 * Avvalora l'attributo costoChilometraggioLimitato legato al listino con l'int in input
		 * @param costo di tipo int
		 */
		public void setCostoKmLimitato(int costo) {
			this.costoChilometraggioLimitato=costo;
		}
		
		/** 
		 * Avvalora l'attributo costoChilometraggioIllimitato 
		 * legato al listino con l'int in input
		 * @param costo di tipo int
		 */
		public void setCostoKmIllimitato(int costo) {
			this.costoChilometraggioIllimitato=costo;
		}
		
		/**
		 * Metodo public che restituisce il valore dell'attributo costoGiornaliero 
		 * legato al ListinoPrezzi
		 * @return int: valore del costo per ogni giorno di noleggio trascorso
		 */
		public int getCostoGiornaliero() {
			return this.costoGiornaliero;
		}
		
		/**
		 * Metodo public che restituisce il valore dell'attributo costoSettimanale
		 * legato al ListinoPrezzi
		 * @return int: valore del costo per ogni settimana di noleggio trascorsa
		 */
		public int getCostoSettimanale() {
			return this.costoSettimanale;
		}
		
		/**
		 * Metodo public che restituisce il valore dell'attributo
		 * costoChilometraggioLimitato legato al ListinoPrezzi
		 * @return int: valore del costo per ogni 50 km di corsa
		 */
		public int getCostoKmLimitato() {
			return this.costoChilometraggioLimitato;
		}
		
		/**
		 * Metodo public che restituisce il valore dell'attributo
		 * costoChilometraggioIllimitato legato al ListinoPrezzi
		 * @return int: valore del costo per ogni km di corsa
		 */
		public int getCostoKmIllimitato() {
			return this.costoChilometraggioIllimitato;
		}
}
