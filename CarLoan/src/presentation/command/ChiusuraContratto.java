package presentation.command;

import java.util.ArrayList;

public class ChiusuraContratto implements Command{
	//attributo di tipo Business Delegato
	
			public Object Execute(String parameter){
				return null;
				
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				float ritorno= 0;
				//istanziare l'attributo e richiedere il servizio
				return ritorno;
			}

}
