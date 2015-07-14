package entity;


public class Operatore {
	/**
	 * Attributo final e private di tipo String: indica il nome dell'operatore
	 */
	private final String nome;
	/**
	 * Attributo final e private di tipo String: indica il cognome dell'operatore
	 */
	private final String cognome;
	/**
	 * Attributo final di tipo String: indica l'indirizzo dell'operatore
	 */
	private String indirizzo;
	/**
	 * Attributo final di tipo String: indica il numero di telefono dell'operatore
	 */
	private String numTelefono;
	/**
	 * Attributo final e private di tipo String: indica il nickname dell'operatore
	 */
	private final String nickname;
	
	/**
	 * Costruttore: istanzia un operatore a partire dai parametri in input
	 * @param nome di tipo String: indica il nome dell' operatore da istanziare
	 * @param cognome di tipo String: indica il cognome dell' operatore da istanziare
	 * @param indirizzo di tipo String: indica l'indirizzo dell' operatore da istanziare
	 * @param tel di tipo String: indica il numero di telefono dell' operatore da istanziare
	 * @param nickname di tipo String: indica il nickname dell' operatore da istanziare
	 */
	public Operatore(String nome, String cognome, String indirizzo, String tel, String nickname) {
		this.nome=nome;
		this.cognome=cognome;
		this.indirizzo=indirizzo;
		this.numTelefono=tel;
		this.nickname=nickname;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo nome legato al operatore
	 * @return String: valore del nome dell' operatore
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo cognome legato al operatore
	 * @return String: valore del cognome dell' operatore
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo indirizzo legato al operatore
	 * @return String: valore dell'indirizzo dell' operatore
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo numTelefono legato al operatore
	 * @return String: valore del numero di telefono dell' operatore
	 */
	public String getNumTelefono() {
		return numTelefono;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo nickname legato al operatore
	 * @return String: valore del nickname dell' operatore
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Avvalora l'attributo indirizzo legato all'operatore con la String in input
	 * @param indirizzo di tipo String.
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo=indirizzo;
	}
	
	/**
	 * Avvalora l'attributo numTelefono legato all'operatore con la String in input
	 * @param numTelefono di tipo String.
	 */
	public void setNumTelefono(String numTelefono) {
		this.numTelefono=numTelefono;
	}
	
}
