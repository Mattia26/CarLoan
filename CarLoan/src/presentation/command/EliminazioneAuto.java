package presentation.command;

import java.util.ArrayList;

public class EliminazioneAuto implements Command {
	
	//attributo di tipo Business Delegato
	
		public Object Execute(String parameter){
			boolean ritorno = true;
			//istanziare l'attributo e richiedere il servizio
			return ritorno;
			
		}

		@Override
		public Object Execute(ArrayList<String> parameters) {
			// TODO Auto-generated method stub
			return null;
		}
	

}
