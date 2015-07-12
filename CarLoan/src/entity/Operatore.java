package entity;


public class Operatore {
	private final String nome;
	private final String cognome;
	private String indirizzo;
	private String numTelefono;
	private final String nickname;
	
	public Operatore(String nome, String cognome, String indirizzo, 
			String numTelefono, String nickname) {
		this.nome=nome;
		this.cognome=cognome;
		this.indirizzo=indirizzo;
		this.numTelefono=numTelefono;
		this.nickname=nickname;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo=indirizzo;
	}
	
	public void setNumTelefono(String numTelefono) {
		this.numTelefono=numTelefono;
	}
	
}
