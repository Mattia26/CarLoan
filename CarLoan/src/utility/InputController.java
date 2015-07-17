package utility;

import java.time.DateTimeException;
import java.time.LocalDate;

/**Classe di utility per il controllo dei dati immessi dall'utente,
 * la manipolazione di date tra interfaccia e database e la trasformazione
 * di stringhe in LocalDate e viceversa
 * 
 * @author Mattia Menna 
 * @author Giuseppe Onesto
 *
 */
public class InputController {

	/**
	 * Pattern per la validazione delle date
	 */
	private static final String DATE_PATTERN = 
	          "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	/**
	 * Pattern per la validazione dei codici fiscali
	 */
	private static final String CODICE_FISCALE_PATTERN =
			"[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	/**
	 * Pattern per la validazione dei numeri di telefono(3 seguito da 9 cifre"
	 */
	private static final String NUM_TEL_PATTERN = "^3\\d{9}$";
	
	/**
	 * Pattern per la validazione di targhe	
	 */
	private static final String TARGA_PATTERN = "[a-zA-Z]{2}\\d{3,4}[a-zA-Z]{2}$";
	
	
	/**Metodo per la validazione di una stringa che rappresenta una data
	 * 
	 * @param t Data da validare
	 * @return booleano esito della validazione
	 * @throws DateTimeException
	 */
	public static boolean dateVerify(String t) throws DateTimeException{
		
		boolean ritorno = false;
		ritorno = t.matches(DATE_PATTERN);
		if(ritorno){
			LocalDate date = InputController.getDate(t);
				
			if(date.isBefore(LocalDate.now()))
				ritorno = false;
			}
		return ritorno;
	}
	
	/**
	 * Metodo per la verifica della correttezza di due date che rappresentano
	 * un lasso di tempo
	 * @param dal Limite inferiore del lasso di tempo
	 * @param al Limite superiore del lasso di tempo
	 * @return booleano che rappresenta l'esito della verifica
	 * @throws DateTimeException
	 */
	public static boolean dateVerify(String dal, String al) throws DateTimeException{
		boolean ritorno = false;
		
		LocalDate Dal = InputController.getDate(dal);
		LocalDate Al = InputController.getDate(al);
		
		if(Al.isAfter(Dal) && Al.isBefore(Dal.plusYears(1)))
			ritorno = true;
		
		return ritorno;
		
	}
	
	/**
	 * Metodo per la verifica della correttezza sintattica di una stringa
	 * rappresentante un codice fiscale
	 * @param t Codice fiscale da validare
	 * @return Booleano esito della validazione
	 */
	public static boolean codiceFiscaleVerify(String t){
		return t.matches(CODICE_FISCALE_PATTERN);
	}
	

	/**
	 * Metodo per la validazione della correttezza sintattica di una stringa
	 * rappresentante un numero di telefono
	 * @param t Numero di telefono da validare
	 * @return Booleano esito della validazione
	 */
	public static boolean telVerify(String t) {
		return t.matches(NUM_TEL_PATTERN);
	}
	
	/**
	 *Metodo per la validazione della correttezza sintattica di una stringa
	 *rappresentante una targa 
	 * @param t
	 * @return
	 */
	public static boolean targaVerify(String t) {
		return t.matches(TARGA_PATTERN);
	}
	/**
	 * Metodo per la verifica della correttezza sintattica dell'id di un contratto
	 * @param t id contratto da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean idContrattoVerify(String t) {
		try {
			if(Integer.parseInt(t)>0)
			return true;
		else 
			return false;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	/**
	 * Metodo per la verifica della correttezza del dato acconto
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean accontoVerify(String t) {
		try {
			if(Integer.parseInt(t)>0)
			return true;
		else 
			return false;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Metodo per la verifica della correttezza del dato ultimoKm
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean ultimoKmVerify(String t) {
		try {
			if(Integer.parseInt(t)>0)
			return true;
		else 
			return false;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Metodo per la verifica del dato modello
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean modelloVerify(String t) {
		if(t.length()<=20 && t.length()>3)
			return true;
		else
			return false;
	}
	
	/**
	 * Metodo per la verifica del dato nome
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean nomeVerify(String t) {
		if(t.length()<=20 && t.length()>3)
			return true;
		else
			return false;
	}
	
	/**
	 * Metodo per la verifica del dato cognome
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean cognomeVerify(String t) {
		if(t.length()<=30 && t.length()>3)
			return true;
		else
			return false;
	}
	/**
	 * Metodo per la verifica del dato indirizzo
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean indirizzoVerify(String t) {
		if(t.length()<=50 && t.length()>9)
			return true;
		else
			return false;
	}
	/**
	 * Metodo per la verifica del dato username
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean usernameVerify(String t) {
		if(t.length()<=20 && t.length()>3)
			return true;
		else
			return false;
	}
	/**
	 * Metodo per la verifica del dato password
	 * @param t Dato da verificare
	 * @return Boolean esito della verifica
	 */
	public static boolean passwordVerify(String t) {
		if(t.length()<=20 && t.length()>4)
			return true;
		else
			return false;
	}
	
	/**
	 * Metodo per la conversione di una stringa in un LocalDate
	 * @param s Stringa da covertire
	 * @return LocalDate rappresentante la stringa in input
	 * @throws DateTimeException
	 */
	public static LocalDate getDate(String s) throws DateTimeException{
		if(s.equals("") || s==null)
			return null;
		
		String[] splittedString = s.split("/");
		LocalDate data;
		if(splittedString.length == 1){
			data = LocalDate.of(Integer.parseInt(splittedString[0]),
					Integer.parseInt(splittedString[1]), Integer.parseInt(splittedString[2]));
		}
		else{
		data = LocalDate.of(Integer.parseInt(splittedString[2]),
				Integer.parseInt(splittedString[1]), Integer.parseInt(splittedString[0]));
		}
		return data;
	}
	/**
	 * Metodo per la conversione di un LocalDate in una stringa
	 * @param date LocalDate da convertire
	 * @return Stringa rappresentante il LocalDate convertito
	 */
	public static String getString(LocalDate date){
		if(date==null)
			return "";
		
		return Integer.toString(date.getDayOfMonth()) + "/" + Integer.toString(date.getMonthValue()) + "/" + Integer.toString(date.getYear());
	}
	/**
	 * Metodo che converte un formato data del tipo "gg/mm/aaaa" in uno del tipo
	 * "aaaa-mm-gg" utilizzabile dal database MySQL
	 * @param s Data da convertire
	 * @return Data convertita
	 */
	public static String stringToMySqlDate(String s){
		if(s==null || s.equals(""))
			return "";
		
		String ritorno;
		String splitted[] = s.split("/");
		
		ritorno = splitted[2] + "-" + splitted[1] + "-" + splitted[0];
		return ritorno;
	}
	/**
	 * Metodo che converte un formato data del tipo "aaaa-mm-gg" in uno del tipo
	 * "gg/mm/aaaa" visualizzabile su un interfaccia
	 * @param s Data da convertire
	 * @return Data convertita
	 */
	public static String mySqlDateToString(String s){
		if(s==null || s.equals(""))
			return "";
		
		String ritorno;
		String splitted[] = s.split("-");
		
		ritorno = splitted[2] + "/" + splitted[1] + "/" + splitted[0];
		return ritorno;
	}
}
