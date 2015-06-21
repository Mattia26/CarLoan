package presentation.command;

import java.util.ArrayList;

public class CercaAuto implements Command{
	//attributo di tipo Business Delegato
	
			public Object Execute(String parameter){
				return null;
				
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				ArrayList<String> ritorno = null;
				//istanziare l'attributo e richiedere il servizio
				return ritorno;
			}

}
