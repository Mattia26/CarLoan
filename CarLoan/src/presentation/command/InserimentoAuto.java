package presentation.command;

import java.util.ArrayList;

public class InserimentoAuto implements Command {
	
	//attributo di tipo Business Delegato
	
	public Object Execute(ArrayList<String> parameters){
		boolean ritorno = true;
		//istanziare l'attributo e richiedere il servizio
		return ritorno;
		
	}

	@Override
	public Object Execute(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
