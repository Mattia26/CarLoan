package entity;

public class Ditta {
	private final String citt�;
	private String indirizzo;
	
	public Ditta(String citt�, String indirizzo) {
		this.citt�=citt�;
		this.indirizzo=indirizzo;
	}
	
	public String getCitt�() {
		return citt�;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String ind) {
		this.indirizzo=ind;
	}
}
