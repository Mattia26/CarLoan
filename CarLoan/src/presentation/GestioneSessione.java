package presentation;

import javafx.scene.Scene;

public class GestioneSessione {
	
	private static String username = null;
	private static String password = null;
	private static Scene sc = null;
	private static String nomeOperatore = null;
	private static String cognomeOperatore = null;
	private static String indirizzoOperatore = null;
	private static String telefonoOperatore = null;
	private static String targaAutoSelezionata = null;
	private static String contrattoSelezionato = null;
	private static String dataInizio = null;
	private static String dataFine = null;
	private static boolean aggiornato = false;
	
	public static String getUsername(){
		return username;
	}
	
	public static void  setUsername(String user){
		username = user;
	}
	
	public static String getPassword(){
		return password;
	}
	
	public static void setPassword(String pass){
		password = pass;
	}
	
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
	
	public static void setTarga(String targa){
		targaAutoSelezionata = targa;
	}
	
	public static String getTarga(){
		return targaAutoSelezionata;
	}
	
	public static void setId(String id){
		contrattoSelezionato = id;
	}
	
	public static String getId(){
		return contrattoSelezionato;
	}
	
	public static void setDataInizio(String dal){
		dataInizio = dal;
	}
	
	public static String getDataInzio(){
		return dataInizio;
	}
	
	public static void setDataFine(String al){
		dataFine = al;
	}
	
	public static String getDataFine(){
		return dataFine;
	}
	
<<<<<<< HEAD
=======

>>>>>>> 13e0fa4d9649716c09e0047e0e822848b7700a6d
	public static boolean getAggiornato(){
		return aggiornato;
	}
	
	public static void setAggiornato(boolean a){
		aggiornato = a;
	}
	public static void azzera(){
		username = null;
		password = null;
		nomeOperatore = null;
		cognomeOperatore = null;
		indirizzoOperatore = null;
		telefonoOperatore = null;
		targaAutoSelezionata = null;
		contrattoSelezionato = null;
		dataInizio = null;
		dataFine = null;
	}

}
