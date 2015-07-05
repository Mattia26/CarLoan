package entity;

public class ListinoPrezzi {
	private int costoGiornaliero;
	private int costoSettimanale;
	private int costoChilometraggioLimitato;
	private int costoChilometraggioIllimitato;
		
		public ListinoPrezzi() {
			costoGiornaliero=10;
			costoSettimanale=60;
			costoChilometraggioLimitato=1;
			costoChilometraggioIllimitato=90;
		}
		public void setCostoGiornaliero(int costo) {
			this.costoGiornaliero=costo;
		}
		
		public void setCostoSettimanale(int costo) {
			this.costoSettimanale=costo;
		}
		
		public void setCostoKmLimitato(int costo) {
			this.costoChilometraggioLimitato=costo;
		}
		
		public void setCostoKmIllimitato(int costo) {
			this.costoChilometraggioIllimitato=costo;
		}
		
		public int getCostoGiornaliero() {
			return this.costoGiornaliero;
		}
		
		public int getCostoSettimanale() {
			return this.costoSettimanale;
		}
		
		public int getCostoKmLimitato() {
			return this.costoChilometraggioLimitato;
		}
		
		public int getCostoKmIllimitato() {
			return this.costoChilometraggioIllimitato;
		}
}
