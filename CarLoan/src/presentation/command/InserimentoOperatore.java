package presentation.command;

import java.util.ArrayList;

public class InserimentoOperatore implements Command{
	//attributo di tipo Business Delegato
	
			public Object Execute(String parameter){
				return null;
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				boolean ritorno = true;
				//istanziare l'attributo e richiedere il servizio
				return ritorno;
			}

}
