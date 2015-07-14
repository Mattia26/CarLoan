package entity;


public class Cliente {
	/**
	 * Attributo final e private di tipo String: indica il nome del cliente
	 */
	private final String nome;
	/**
	 * Attributo final e private di tipo String: indica il cognome del cliente
	 */
	private final String cognome;
	/**
	 * Attributo private di tipo String: indica il numero di telefono del cliente
	 */
	private String numeroTelefono;
	/**
	 * Attributo final e private di tipo String: indica il codice fiscale del cliente
	 */
	private final String codFiscale;
	
	/**
	 * Costruttore: istanzia un cliente a partire dai parametri in input
	 * @param nome di tipo String: indica il nome del cliente da istanziare
	 * @param cognome di tipo String: indica il cognome del cliente da istanziare
	 * @param numeroTelefono di tipo String: indica il numero di telefono
	 *  del cliente da istanziare
	 * @param codFiscale di tipo String: indica il codice fiscale del cliente da istanziare
	 */
	public Cliente(String nome, String cognome, String numeroTelefono, String codFiscale) {
		this.nome=nome;
		this.cognome=cognome;
		this.numeroTelefono=numeroTelefono;
		this.codFiscale=codFiscale;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo nome legato al cliente
	 * @return String: valore del nome del cliente
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo cognome legato al cliente
	 * @return String: valore del cognome del cliente
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo numeroTelefono legato al cliente
	 * @return String: valore del numero di telefono del cliente
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo codFiscale legato al cliente
	 * @return String: valore del codice fiscale del cliente
	 */
	public String getCodFiscale() {
		return codFiscale;
	}
	
	/**
	 * Avvalora l'attributo numeroTelefono legato al cliente con la String in input
	 * @param numeroTelefono di tipo String.
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono=numeroTelefono;
	}
	
}
