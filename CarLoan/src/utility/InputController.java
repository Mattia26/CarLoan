package utility;

import java.time.LocalDate;



public class InputController {
	
	private static final String DATE_PATTERN = 
	          "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	private static final String CODICE_FISCALE_PATTERN =
			"[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	
	public static boolean dateVerify(String t) {
		boolean ritorno = false;
		ritorno = t.matches(DATE_PATTERN);
		if(ritorno == true){
			LocalDate date = InputController.getCalendar(t);
			if(date.isBefore(LocalDate.now()))
				ritorno = false;
		}
		return ritorno;
		
	}
	
	public static boolean dateVerify(String dal, String al){
		boolean ritorno = false;
		
		LocalDate Dal = InputController.getCalendar(dal);
		LocalDate Al = InputController.getCalendar(al);
		
		if(Dal.isBefore(Al) || Dal.isEqual(Al))
			ritorno = true;
		
		return ritorno;
		
	}
	
	public static boolean codiceFiscaleVerify(String t){
		return t.matches(CODICE_FISCALE_PATTERN);
	}
	
	public static LocalDate getCalendar(String s){
		String[] splittedString = s.split("/");
		LocalDate data = LocalDate.of(Integer.parseInt(splittedString[2]),Integer.parseInt(splittedString[1]), Integer.parseInt(splittedString[0]));
		
		return data;
	}
	
	public static LocalDate getCalendarDB(String s){
		String[] splittedString = s.split("-");
		LocalDate data = LocalDate.of(Integer.parseInt(splittedString[0]),Integer.parseInt(splittedString[1]), Integer.parseInt(splittedString[2]));
		
		return data;
	}
}
