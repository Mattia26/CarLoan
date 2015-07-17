package presentation;

import java.util.ArrayList;

import javafx.scene.Scene;
/**
 * Classe che mantiene i dati che sono utili durante la durata dell'esecuzione del
 * programma, ogni attributo pu√≤ essere letto o modificato tramite i corrispondenti
 * metodi statici get e set
 * 
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class GestioneSessione {
	/**
	 * Scena corrente
	 */
	private static Scene sc = null;
	/**
	 * username dell'utente correntemente loggato
	 */
	private static String username = null;
	/**
	 * Nome dell'operatore correntemente loggato
	 */
	private static String nomeOperatore = null;
	/**
	 * Cognome dell'operatore correntemente loggato
	 */
	private static String cognomeOperatore = null;
	/**
	 * Indirizzo dell'operatore correntemente loggato
	 */
	private static String indirizzoOperatore = null;
	/**
	 * Telefono dell'operatore correntemente loggato
	 */
	private static String telefonoOperatore = null;
	/**
	 * Nome di un cliente
	 */
	private static String nomeCliente = null;
	/**
	 * Cognome di un  cliente
	 */
	private static String cognomeCliente = null;
	/**
	 * Telefono di un cliente
	 */
	private static String telefonoCliente = null;
	/**
	 * Codice fiscale di un cliente
	 */
	private static String cfCliente = null;
	/**
	 * Targa dell'auto selezionata
	 */
	private static String targaAutoSelezionata = null;
	/**
	 * Ultimo chilometraggio registrato da un auto
	 */
	private static String ultimoKmAuto = null;
	/**
	 * Id del contratto selezionato
	 */
	private static String idcontrattoSelezionato = null;
	/**
	 * Data inizio contratto selezionato
	 */
	private static String dataInizio = null;
	/**
	 * Data fine contratto selezionato
	 */
	private static String dataFine = null;
	/**
	 * Acconto versato per il contratto selezionato
	 */
	private static String acconto = null;
	/**
	 * Tipologia del contratto selezionato
	 */
	private static String tipologiaContratto = null;
	/**
	 * Tipo chilometraggio del contratto selezionato
	 */
	private static String tipoKmContratto = null;
	/**
	 * Sede di restituzione del contratto selezionato
	 */
	private static String sedeRestituzione = null;
	
	private static String costoGiornaliero = null;
	
	private static String costoSettimanale = null;
	
	private static String costoKmLimitato = null;
	
	private static String costoKmIllimitato = null;
	
	private static ArrayList<String> ditta = null;
	/**
	 * Flag per l'aggiornamento periodico del database
	 */
	private static boolean aggiornato = false;
	
	/**
	 * @return username
	 */
	public static String getUsername(){
		return username;
	}
	
	/**
	 * Setta l'username dell'utente correntemente loggato
	 * @param user Nuovo username
	 */
	public static void  setUsername(String user){
		username = user;
	}
	
	/**
	 * @return sc
	 */
	public static Scene getScene(){
		return sc;
	}
	
	/**
	 * Setta la scena corrente
	 * @param scene Nuova Scena
	 */
	public static void setScene(Scene scene){
		sc = scene;
	}
	
	/**
	 * @return nomeOperatore
	 */
	public static String getNomeOperatore(){
		return nomeOperatore;
	}
	
	/**
	 * Setta il nome dell'operatore correntemente loggato
	 * @param nome Nuovo nome 
	 */
	public static void setNomeOperatore(String nome){
		nomeOperatore = nome;
	}
	
	/**
	 * @return cognomeOperatore
	 */
	public static String getCognomeOperatore(){
		return cognomeOperatore;
	}
	
	/**
	 * Setta il cognome dell'operatore correntemente loggato
	 * @param cognome Nuovo cognome
	 */
	public static void setCognomeOperatore(String cognome){
		cognomeOperatore = cognome;
	}
	
	/**
	 * @return indirizzoOperatore
	 */
	public static String getIndirizzoOperatore() {
		return indirizzoOperatore;
	}
	
	/**
	 * Setta l'indirizzo dell'operatore correntemente loggato
	 * @param indirizzo Indirizzo operatore
	 */
	public static void setIndirizzoOperatore(String indirizzo) {
		indirizzoOperatore=indirizzo;
	}
	
	/**
	 * @return telefonoOperatore
	 */
	public static String getTelefonoOperatore(){
		return telefonoOperatore;
	}
	
	/**
	 * Setta il numero di telefonno dell'operatore correntemente loggato
	 * @param telefono Nuovo numero di telefono
	 */
	public static void setTelefonoOperatore(String telefono){
		telefonoOperatore=telefono;
	}
	
	/**
	 * @return nomeCliente
	 */
	public static String getNomeCliente() {
		return nomeCliente;
	}
	
	/**
	 * Setta il nome di un cliente
	 * @param nome Nuovo nome
	 */
	public static void setNomeCliente(String nome) {
		nomeCliente=nome;
	}
	
	/**
	 * @return cognomeCliente
	 */
	public static String getCognomeCliente() {
		return cognomeCliente;
	}
	
	/**
	 * Setta il cognome di un cliente
	 * @param cognome Nuovo cognome
	 */
	public static void setCognomeCliente(String cognome) {
		cognomeCliente=cognome;
	}
	
	/**
	 * @return telefonoCliente
	 */
	public static String getTelefonoCliente() {
		return telefonoCliente;
	}
	
	/**
	 * Setta il numero di telefono di un cliente
	 * @param tel Nuovo numero di telefono
	 */
	public static void setTelefonoCliente(String tel) {
		telefonoCliente=tel;
	}
	
	/**
	 * @return cfCliente
	 */
	public static String getCFCliente() {
		return cfCliente;
	}
	
	/**
	 * Setta il codice fiscale di un cliente
	 * @param cf Nuovo codice fiscale
	 */
	public static void setCFCliente(String cf) {
		cfCliente=cf;
	}
	
	
	/**
	 * @return targaAutoSelezionata
	 */
	public static String getTarga(){
		return targaAutoSelezionata;
	}
	
	/**
	 * Setta la targa dell'auto selezionata per un contratto
	 * @param targa Nuova targa
	 */
	public static void setTarga(String targa){
		targaAutoSelezionata = targa;
	}
	
	/**
	 * 
	 * @return ultimoKmAuto
	 */
	public static String getUltimoKm() {
		return ultimoKmAuto;
	}
	
	/**
	 * Setta l'ultimo chilometraggio dell'auto selezionata per un contratto
	 * @param ultKm Nuovo chilometraggio
	 */
	public static void setUltimoKm(Double ultKm) {
		ultimoKmAuto = ultKm.toString();
	}
	
	/**
	 * Setta l'id del contratto selezionato
	 * @param id Nuovo id
	 */
	public static void setId(Integer id){
		idcontrattoSelezionato = id.toString();
	}
	
	/**
	 * 
	 * @return idContrattoSelezionato
	 */
	public static String getId(){
		return idcontrattoSelezionato;
	}
	
	/**
	 * Setta la data di inizio del contratto selezionato
	 * @param data Nuova data inizio contratto
	 */
	public static void setDataInizio(String data) {
		dataInizio = data;
	}
	/**
	 * 
	 * @return dataInizio
	 */
	public static String getDataInizio() {
		return dataInizio;
	}
	
	/**
	 * Setta la data di fine del contratto selezionato
	 * @param data Nuova data di fine contratto
	 */
	public static void setDataFine(String data) {
		dataFine = data;
	}
	/**
	 * 
	 * @return dataFine
	 */
	public static String getDataFine() {
		return dataFine;
	}
	
	/**
	 * Setta il tipo del contratto selezionato 
	 * @param tipo Nuovo tipo 
	 */
	public static void setTipoContratto(Character tipo) {
		tipologiaContratto=tipo.toString();
	}
	
	/**
	 * 
	 * @return tipologiaContratto
	 */
	public static String getTipoContratto() {
		return tipologiaContratto;
	}
	
	/**
	 * Setta il tipo di chilometraggio del contratto selezionato
	 * @param tipo Nuovo tipo chilometraggio
	 */
	public static void setTipoKmContratto(Character tipo) {
		tipoKmContratto=tipo.toString();
	}
	/**
	 * 
	 * @return tipoKmContratto
	 */
	public static String getTipologiaKmContratto() {
		return tipoKmContratto;
	}
	/**
	 * Setta l'acconto versato per il contratto selezionato
	 * @param acc Nuovo acconto
	 */
	public static void setAcconto(Integer acc) {
		acconto = acc.toString();
	}
	
	/**
	 * 
	 * @return acconto
	 */
	public static String getAcconto() {
		return acconto;
	}
	
	/**
	 * Setta la sede di restituzione per il contratto corrente
	 * @param sede Nuova sede
	 */
	public static void setSedeRestituzione(String sede) {
		sedeRestituzione = sede;
	}
	
	/**
	 * 
	 * @return sedeRestituzione
	 */
	public static String getSedeRestituzione() {
		return sedeRestituzione;
	}
	
	/**
	 * Setta il costo per un giorno di noleggio in base ai prezzi attualmente in vigore
	 * @param costo: importo da versare per ogni giorno di noleggio
	 */
	public static void setCostoGiornaliero(int costo) {
		costoGiornaliero = String.valueOf(costo);
	}
	
	/**
	 * 
	 * @return costoGiornaliero
	 */
	public static String getCostoGiornaliero() {
		return costoGiornaliero;
	}

	/**
	 * Setta il costo per una settimana di noleggio in base ai prezzi attualmente in vigore
	 * @param costo: importo da versare per ogni settimana di noleggio
	 */
	public static void setCostoSettimanale(int costo) {
		costoSettimanale = String.valueOf(costo);
	}
	
	/**
	 * 
	 * @return costoSettimanale
	 */
	public static String getCostoSettimanale() {
		return costoSettimanale;
	}
	
	/**
	 * Setta il costo per ogni 50 km di corsa in base ai prezzi attualmente in vigore
	 * @param costo: importo da versare per ogni 50 km di corsa
	 */
	public static void setCostoKmLimitato(int costo) {
		costoKmLimitato = String.valueOf(costo);
	}
	
	/**
	 * 
	 * @return costoKmLimitato
	 */
	public static String getCostoKmLimitato() {
		return costoKmLimitato;
	}

	/**
	 * Setta il costo per ogni km di corsa in base ai prezzi attualmente in vigore
	 * @param costo: importo da versare per ogni km di corsa
	 */
	public static void setCostoKmIllimitato(int costo) {
		costoKmIllimitato = String.valueOf(costo);
	}
	
	/**
	 * 
	 * @return costoKmIllimitato
	 */
	public static String getCostoKmIllimitato() {
		return costoKmIllimitato;
	}

	
	/**
	 * 
	 * @return aggiornato
	 */
	public static boolean getAggiornato(){
		return aggiornato;
	}
	
	/**
	 * Setta il flag aggiornato
	 * @param a Nuovo valore del flag
	 */
	public static void setAggiornato(boolean a){
		aggiornato = a;
	}
	
	public static void setDitta(ArrayList<String> citt‡) {
		// TODO Auto-generated method stub
		ditta = citt‡;
	}
	
	public static ArrayList<String> getDitta() {
		// TODO Auto-generated method stub
		return ditta;
	}
	
	/**
	 * Setta a null tutti i valori di sessione
	 */
	public static void azzera(){
		
		username = null;
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
