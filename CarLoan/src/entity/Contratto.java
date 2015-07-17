package entity;


public class Contratto {
	/**
	 * Attributo final e private di tipo int: indica l'id del contratto
	 */
	private final int id;
	/**
	 * Attributo final e private di tipo String: indica il codice fiscale del cliente 
	 * che stipula o ha stipulato il contratto
	 */
	private final String codFiscaleCliente;
	/**
	 * Attributo final e private di tipo String: indica la targa dell'auto noleggiata
	 * nel contratto
	 */
	private final String targaMacchina;
	/**
	 * Attributo private di tipo String: indica la data di inizio del noleggio
	 */
	private final String dataInizio;
	/**
	 * Attributo private di tipo String: indica la data di fine del noleggio
	 */
	private String dataFine;
	
	/**
	 * Attributo private di tipo int: indica l'importo dell'acconto versato
	 */
	private int acconto;
	/**
	 * Attributo private di tipo char: indica il tipo di noleggio
	 */
	private char tipologia;
	/**
	 * Attributo private di tipo char: indica il tipo di chilometraggio
	 */
	private char tipoChilometraggio;
	/**
	 * Attributo private di tipo String: indica la sede di restituzione dell'auto
	 */
	private String sedeRestituzione;
	/**
	 * Attributo private di tipo boolean: indica se il contratto è stato chiuso o meno
	 */
	private boolean chiuso;
	/**
	 * Attributo private di tipo boolean: indica se l'auto da noleggiare per tale 
	 * contratto è stata ritirata.
	 */
	private boolean macchinaRitirata;
	
	/**
	 * Costruttore: istanzia un contratto a partire dai parametri in input
	 * @param id di tipo int: indica l'id del contratto da istanziare
	 * @param codFiscaleCliente di tipo String: indica il codice fiscale del cliente 
	 * che stipula o ha stipulato il contratto da istanziare
	 * @param targaMacchina di tipo String: indica la targa dell'auto noleggiata
	 * nel contratto da istanziare
	 * @param dataInizio di tipo String: indica la data di inizio del contratto
	 * @param dataFine di tipo String: indica la data di fine del contratto
	 * @param acconto di tipo int: indica l'importo dell'acconto versato
	 * @param tipo di tipo char: indica il tipo di noleggio
	 * @param tipoKm di tipo char: indica il tipo di chilometraggio
	 * @param sedeRestituzione di tipo String: indica la sede di restituzione dell'auto
	 * @param chiuso di tipo boolean: indica se il contratto è stato chiuso o meno
	 * @param macchinaRitirata di tipo boolean: indica se l'auto da noleggiare per tale 
	 * contratto è stata ritirata.
	 */
	public Contratto(int id, String codFiscaleCliente, String targaMacchina, String dataInizio, 
			String dataFine, int acconto, char tipo, char tipoKm, String sedeRestituzione,
			boolean chiuso, boolean macchinaRitirata) {
		this.id=id;
		this.codFiscaleCliente=codFiscaleCliente;
		this.targaMacchina=targaMacchina;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
		this.acconto=acconto;
		this.tipologia=tipo;
		this.tipoChilometraggio=tipoKm;
		this.sedeRestituzione=sedeRestituzione;
		this.chiuso=chiuso;
		this.macchinaRitirata=macchinaRitirata;
	}
	
	
	/**
	 * Avvalora l'attributo dataFine legato al contratto con la String in input
	 * @param data di tipo String.
	 */
	public void setDataFine(String data) {
		this.dataFine=data;
	}
	
	/**
	 * Avvalora l'attributo acconto legato al contratto con l'int in input
	 * @param quotaAcconto di tipo int.
	 */
	public void setAcconto(int quotaAcconto) {
		this.acconto=quotaAcconto;
	}
	
	/**
	 * Avvalora l'attributo tipologia legato al contratto con il char in input
	 * @param tipo di tipo char.
	 */
	public void setTipoContratto(char tipo) {
		this.tipologia=tipo;
	}
	
	/**
	 * Avvalora l'attributo tipoChilometraggio legato al contratto con il char in input
	 * @param tipoKm di tipo char.
	 */
	public void setTipoChilometraggio(char tipoKm) {
		this.tipoChilometraggio=tipoKm;
	}
	
	/**
	 * Avvalora l'attributo sedeRestituzione legato al contratto con la String in input
	 * @param sede di tipo String.
	 */
	public void setSedeRestituzione(String sede) {
		this.sedeRestituzione=sede;
	}
	
	/**
	 * Avvalora l'attributo chiuso legato al contratto con il boolean in input
	 * @param chiuso di tipo boolean.
	 */
	public void setChiuso(boolean chiuso) {
		this.chiuso=chiuso;
	}
	
	/**
	 * Avvalora l'attributo chiuso legato al contratto con il boolean in input
	 * @param chiuso di tipo boolean.
	 */
	public void setRitirata(boolean ritiro) {
		this.macchinaRitirata=ritiro;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo id legato al contratto
	 * @return int: valore dell'id del contratto
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo 
	 * codFiscaleCliente legato al contratto
	 * @return String: valore del codice fiscale del cliente che ha stipulato il contratto
	 */
	public String getCliente() {
		return this.codFiscaleCliente;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo targaMacchina legato al contratto
	 * @return String: valore della targa dell'auto da noleggiare o noleggiata.
	 */
	public String getTargaMacchina() {
		return this.targaMacchina;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo dataInizio legato al contratto
	 * @return String: valore della data di inizio del contratto.
	 */
	public String getDataInizio() {
		return this.dataInizio;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo dataFine legato al contratto
	 * @return String: valore della data di fine del contratto.
	 */
	public String getDataFine() {
		return this.dataFine;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo acconto legato al contratto
	 * @return int: valore dell'acconto versato.
	 */
	public int getQuotaAcconto() {
		return this.acconto;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo tipologia legato al contratto
	 * @return char: valore del tipo di noleggio scelto.
	 */
	public char getTipologia() {
		return this.tipologia;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo 
	 * tipoChilometraggio legato al contratto
	 * @return char: valore del tipo di chilometraggio scelto.
	 */
	public char getTipoChilometraggio() {
		return this.tipoChilometraggio;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo 
	 * sedeRestituzione legato al contratto
	 * @return String: valore della sede di restituzione dell'auto.
	 */
	public String sedeRestituzione() {
		return this.sedeRestituzione;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo chiuso legato al contratto
	 * @return boolean: true se il contratto è chiuso, false altrimenti.
	 */
	public boolean chiuso() {
		return chiuso;
	}
	
	/**
	 * Metodo public che restituisce il valore dell'attributo 
	 * macchinaRitirata legato al contratto
	 * @return boolean: true se l'auto è stata ritirata per il noleggio relativo 
	 * a questo contratto, false altrimenti.
	 */
	public boolean macchinaRitirata() {
		return macchinaRitirata;
	}
}
