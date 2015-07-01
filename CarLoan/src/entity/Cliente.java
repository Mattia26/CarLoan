package entity;


public class Cliente {
	private final String nome;
	private final String cognome;
	private int numeroTelefono;
	private final String codFiscale;
	
	public Cliente(String nome, String cognome, int numeroTelefono, String codFiscale) {
		this.nome=nome;
		this.cognome=cognome;
		this.numeroTelefono=numeroTelefono;
		this.codFiscale=codFiscale;
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
	
	public String getCodFiscale() {
		return codFiscale;
	}
	
	public void setNumeroTelefono(int numeroTelefono) {
		this.numeroTelefono=numeroTelefono;
	}
	
}
