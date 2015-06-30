package entity;

public class Cliente {
	private final String nome;
	private final String cognome;
	private int numeroTelefono;
	private final String codiceFiscale;
	
	public Cliente(String nome, String cognome, int numeroTelefono,String CF) {
		this.nome=nome;
		this.cognome=cognome;
		this.numeroTelefono=numeroTelefono;
		this.codiceFiscale = CF;
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public int getNumeroTelefono() {
		return numeroTelefono;
	}
	
	public String getCF() {
		return codiceFiscale;
	}
	
	public void setNumeroTelefono(int numeroTelefono) {
		this.numeroTelefono=numeroTelefono;
	}
	
	
}
