package presentation.command;

import java.util.ArrayList;

import presentation.GestioneSessione;

public class ModificaDatiOperatore implements Command{
	//attributo di tipo Business Delegato
	
			public Object Execute(String parameter){
				return null;
				
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				boolean ritorno=false;
				GestioneSessione.setNomeOperatore(parameters.get(0));
				GestioneSessione.setCognomeOperatore(parameters.get(1));
				GestioneSessione.setTelefonoOperatore(parameters.get(2));
				//istanziare il BusinessDelegate e richiedere il servizio
				return ritorno;
			}

}
