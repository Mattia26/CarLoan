package presentation.command;

import java.util.ArrayList;
import java.util.HashMap;

public class CercaAuto implements Command{
	//attributo di tipo Business Delegato
	
			public Object Execute(String parameter){
				return null;
				
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				ArrayList<String> ritorno = new ArrayList<String>();
				ritorno.add("111111 Alfa Tipo A 10000km");
				ritorno.add("222222 Toyota Tipo A 20000km");
				
				//istanziare l'attributo e richiedere il servizio
				return ritorno;
			}

}
