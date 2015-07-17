package utility;

import java.time.DateTimeException;
import java.time.LocalDate;



public class InputController {
	
	private static final String DATE_PATTERN = 
	          "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	private static final String CODICE_FISCALE_PATTERN =
			"[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	private static final String NUM_TEL_PATTERN = "^3\\d{9}$";
	private static final String TARGA_PATTERN = "[a-zA-Z]{2}\\d{3,4}[a-zA-Z]{2}$";
	
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
	
	public static boolean dateVerify(String dal, String al) throws DateTimeException{
		boolean ritorno = false;
		
		LocalDate Dal = InputController.getDate(dal);
		LocalDate Al = InputController.getDate(al);
		
		if(Al.isAfter(Dal) && Al.isBefore(Dal.plusYears(1)))
			ritorno = true;
		
		return ritorno;
		
	}
	
	public static boolean codiceFiscaleVerify(String t){
		return t.matches(CODICE_FISCALE_PATTERN);
	}
	
	public static boolean telVerify(String t) {
		return t.matches(NUM_TEL_PATTERN);
	}
	
	public static boolean targaVerify(String t) {
		return t.matches(TARGA_PATTERN);
	}
	
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
	
	public static boolean modelloVerify(String t) {
		if(t.length()<=20 && t.length()>3)
			return true;
		else
			return false;
	}
	
	public static boolean nomeVerify(String t) {
		if(t.length()<=20 && t.length()>3)
			return true;
		else
			return false;
	}
	
	public static boolean cognomeVerify(String t) {
		if(t.length()<=30 && t.length()>3)
			return true;
		else
			return false;
	}
	
	public static boolean indirizzoVerify(String t) {
		if(t.length()<=50 && t.length()>9)
			return true;
		else
			return false;
	}
	
	public static boolean usernameVerify(String t) {
		if(t.length()<=20 && t.length()>3)
			return true;
		else
			return false;
	}
	
	public static boolean passwordVerify(String t) {
		if(t.length()<=20 && t.length()>4)
			return true;
		else
			return false;
	}
	
	
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
	
	public static String getString(LocalDate date){
		if(date==null)
			return "";
		
		return Integer.toString(date.getDayOfMonth()) + "/" + Integer.toString(date.getMonthValue()) + "/" + Integer.toString(date.getYear());
	}
	
	public static String stringToMySqlDate(String s){
		if(s==null || s.equals(""))
			return "";
		
		String ritorno;
		String splitted[] = s.split("/");
		
		ritorno = splitted[2] + "-" + splitted[1] + "-" + splitted[0];
		return ritorno;
	}
	
	public static String mySqlDateToString(String s){
		if(s==null || s.equals(""))
			return "";
		
		String ritorno;
		String splitted[] = s.split("-");
		
		ritorno = splitted[2] + "/" + splitted[1] + "/" + splitted[0];
		return ritorno;
	}
}
