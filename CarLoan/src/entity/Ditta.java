package entity;

public class Ditta {
	private final String città;
	private String indirizzo;
	
	public Ditta(String città, String indirizzo) {
		this.città=città;
		this.indirizzo=indirizzo;
	}
	
	public String getCittà() {
		return città;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String ind) {
		this.indirizzo=ind;
	}
}
