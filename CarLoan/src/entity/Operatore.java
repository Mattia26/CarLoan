package entity;

public class Operatore {
	private final String nome;
	private final String cognome;
	private int età;
	private final String nickname;
	
	public Operatore(String nome, String cognome, int età, String nickname) {
		this.nome=nome;
		this.cognome=cognome;
		this.età=età;
		this.nickname=nickname;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getEtà() {
		return età;
	}
	
	public String getNickname() {
		return nickname;
	}
	
}
