package presentation;

import javafx.scene.Scene;

public class GestioneSessione {
	
	private static String username = null;
	private static String password = null;
	private static Scene sc = null;
	private static String nomeOperatore = null;
	private static String cognomeOperatore = null;
	private static String telefonoOperatore = null;
	private static String targaAutoSelezionata = null;
	
	public static String getUsername(){
		return GestioneSessione.username;
	}
	
	public static void  setUsername(String name){
		GestioneSessione.username = name;
	}
	
	public static String getPassword(){
		return GestioneSessione.password;
	}
	
	public static void setPassword(String pass){
		GestioneSessione.password = pass;
	}
	
	public static Scene getScene(){
		return GestioneSessione.sc;
	}
	
	public static void setScene(Scene scene){
		GestioneSessione.sc = scene;
	}
	
	public static String getNomeOperatore(){
		return GestioneSessione.nomeOperatore;
	}
	
	public static void setNomeOperatore(String nome){
		GestioneSessione.nomeOperatore = nome;
	}
	
	public static String getCognomeOperatore(){
		return GestioneSessione.cognomeOperatore;
	}
	
	public static void setCognomeOperatore(String cognome){
		GestioneSessione.cognomeOperatore = cognome;
	}
	
	public static String getTelefonoOperatore(){
		return GestioneSessione.telefonoOperatore;
	}
	
	public static void setTelefonoOperatore(String telefono){
		GestioneSessione.telefonoOperatore=telefono;
		
	}
	
	public static void setTarga(String targa){
		GestioneSessione.targaAutoSelezionata = targa;
	}
	
	public static String getTarga(){
		return GestioneSessione.targaAutoSelezionata;
	}
	
	public static void azzera(){
		GestioneSessione.username = null;
		GestioneSessione.password = null;
		GestioneSessione.nomeOperatore = null;
		GestioneSessione.cognomeOperatore = null;
		GestioneSessione.telefonoOperatore = null;
		GestioneSessione.targaAutoSelezionata = null;
	}
}
