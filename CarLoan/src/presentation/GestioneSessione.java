package presentation;

import javafx.scene.Scene;

public class GestioneSessione {
	
	private static Scene sc = null;
	private static String username = null;
	//private static String password = null;
	private static String nomeOperatore = null;
	private static String cognomeOperatore = null;
	private static String indirizzoOperatore = null;
	private static String telefonoOperatore = null;
	private static String nomeCliente = null;
	private static String cognomeCliente = null;
	private static String telefonoCliente = null;
	private static String cfCliente = null;
	private static String targaAutoSelezionata = null;
	private static String ultimoKmAuto = null;
	private static String idcontrattoSelezionato = null;
	private static String dataInizio = null;
	private static String dataFine = null;
	private static String acconto = null;
	private static String tipologiaContratto = null;
	private static String tipoKmContratto = null;
	private static String sedeRestituzione = null;
	private static boolean aggiornato = false;
	
	public static String getUsername(){
		return username;
	}
	
	public static void  setUsername(String user){
		username = user;
	}
	
	/*public static String getPassword(){
		return password;
	}
	
	public static void setPassword(String pass){
		password = pass;
	}*/
	
	public static Scene getScene(){
		return sc;
	}
	
	public static void setScene(Scene scene){
		sc = scene;
	}
	
	public static String getNomeOperatore(){
		return nomeOperatore;
	}
	
	public static void setNomeOperatore(String nome){
		nomeOperatore = nome;
	}
	
	public static String getCognomeOperatore(){
		return cognomeOperatore;
	}
	
	public static void setCognomeOperatore(String cognome){
		cognomeOperatore = cognome;
	}
	
	public static String getIndirizzoOperatore() {
		return indirizzoOperatore;
	}
	
	public static void setIndirizzoOperatore(String indirizzo) {
		indirizzoOperatore=indirizzo;
	}
	
	public static String getTelefonoOperatore(){
		return telefonoOperatore;
	}
	
	public static void setTelefonoOperatore(String telefono){
		telefonoOperatore=telefono;
	}
	
	public static String getNomeCliente() {
		return nomeCliente;
	}
	
	public static void setNomeCliente(String nome) {
		nomeCliente=nome;
	}
	
	public static String getCognomeCliente() {
		return cognomeCliente;
	}
	
	public static void setCognomeCliente(String cognome) {
		cognomeCliente=cognome;
	}
	
	public static String getTelefonoCliente() {
		return telefonoCliente;
	}
	
	public static void setTelefonoCliente(String tel) {
		telefonoCliente=tel;
	}
	
	public static String getCFCliente() {
		return cfCliente;
	}
	
	public static void setCFCliente(String cf) {
		cfCliente=cf;
	}
	
	public static String getTarga(){
		return targaAutoSelezionata;
	}
	
	public static void setTarga(String targa){
		targaAutoSelezionata = targa;
	}
	
	public static String getUltimoKm() {
		return ultimoKmAuto;
	}
	
	public static void setUltimoKm(Double ultKm) {
		ultimoKmAuto = ultKm.toString();
	}
	
	public static void setId(Integer id){
		idcontrattoSelezionato = id.toString();
	}
	
	public static String getId(){
		return idcontrattoSelezionato;
	}
	
	public static void setDataInizio(String data) {
		dataInizio = data;
	}
	
	public static String getDataInizio() {
		return dataInizio;
	}
	
	public static void setDataFine(String data) {
		dataFine = data;
	}
	
	public static String getDataFine() {
		return dataFine;
	}
	
	public static void setTipoContratto(Character tipo) {
		tipologiaContratto=tipo.toString();
	}
	
	public static String getTipoContratto() {
		return tipologiaContratto;
	}
	
	public static void setTipoKmContratto(Character tipo) {
		tipoKmContratto=tipo.toString();
	}
	
	public static String getTipologiaKmContratto() {
		return tipoKmContratto;
	}
	
	public static void setAcconto(Integer acc) {
		acconto = acc.toString();
	}
	
	public static String getAcconto() {
		return acconto;
	}
	
	public static void setSedeRestituzione(String sede) {
		sedeRestituzione = sede;
	}
	
	public static String getSedeRestituzione() {
		return sedeRestituzione;
	}
	
	public static boolean getAggiornato(){
		return aggiornato;
	}
	
	public static void setAggiornato(boolean a){
		aggiornato = a;
	}
	public static void azzera(){
		
		username = null;
		//password = null;
		nomeOperatore = null;
		cognomeOperatore = null;
		indirizzoOperatore = null;
		telefonoOperatore = null;
		nomeCliente = null;
		cognomeCliente = null;
		telefonoCliente = null;
		cfCliente = null;
		targaAutoSelezionata = null;
		ultimoKmAuto = null;
		idcontrattoSelezionato = null;
		acconto = null;
		tipologiaContratto = null;
		tipoKmContratto = null;
		sedeRestituzione = null;
		aggiornato = false;
		
	}

}
