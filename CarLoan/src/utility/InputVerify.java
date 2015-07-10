package utility;

import java.time.LocalDate;



public class InputVerify {
	
	private static final String DATE_PATTERN = 
	          "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	private static final String CODICE_FISCALE_PATTERN =
			"[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	
	public static boolean dateVerify(String t) {
		boolean ritorno = false;
		ritorno = t.matches(DATE_PATTERN);
		System.out.println(ritorno);
		if(ritorno == true){
			LocalDate date = InputController.getDate(t);
			System.out.println(date);
			if(date.isBefore(LocalDate.now()))
				ritorno = false;
		}
		return ritorno;
		
	}
	
	public static boolean codiceFiscaleVerify(String t){
		return t.matches(CODICE_FISCALE_PATTERN);
	}
}
