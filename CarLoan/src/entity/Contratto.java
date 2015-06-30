package entity;

public class Contratto {
	private final String codiceFiscale;
	private String targaMacchina;
	private String dataInizio;
	private String dataFine;
	private int acconto;
	private char tipologia;
	private char tipoChilometraggio;
	private String sedeRestituzione;
	
	public Contratto(String CF, String targaMacchina, String dataInizio, 
			String dataFine, int acconto, char tipo, char tipoKm, String sede) {
		this.codiceFiscale=CF;
		this.targaMacchina=targaMacchina;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
		this.acconto=acconto;
		this.tipologia=tipo;
		this.tipoChilometraggio=tipoKm;
		this.sedeRestituzione=sede;
	
	}
	
	public void setMacchina(String targa) {
		this.targaMacchina=targa;
	}
	
	public void setDataInizio(String data) {
		this.dataInizio=data;
	}
	
	public void setDataFine(String data) {
		this.dataFine=data;
	}
	
	public void setAcconto(int quotaAcconto) {
		this.acconto=quotaAcconto;
	}
	
	public void setTipoContratto(char tipo) {
		this.tipologia=tipo;
	}
	
	public void setTipoChilometraggio(char tipoKm) {
		this.tipoChilometraggio=tipoKm;
	}
	
	public void setSedeRestituzione(String sede) {
		this.sedeRestituzione=sede;
	}
	
	
	
	public String getTargaMacchina() {
		return this.targaMacchina;
	}
	
	public String getDataInizio() {
		return this.dataInizio;
	}
	
	public String getDataFine() {
		return this.dataFine;
	}
	
	public int getQuotaAcconto() {
		return this.acconto;
	}
	
	public char getTipologia() {
		return this.tipologia;
	}
	
	public char getTipoChilometraggio() {
		return this.tipoChilometraggio;
	}
	
	public String sedeRestituzione() {
		return this.sedeRestituzione;
	}
	
	public String getCF(){
		return this.codiceFiscale;
	}
}
