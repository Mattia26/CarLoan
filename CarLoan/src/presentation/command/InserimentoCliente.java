package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
/**
 * Classe che si occupa dell'inserimento di un cliente nel database
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class InserimentoCliente implements Command {
	
	BusinessDelegate b;
	
	public Object Execute(ArrayList<String> parameters){
		boolean ritorno = false;
		b = new BusinessDelegate();
		
		try {
			ritorno = (boolean)b.handleRequest("InserisciCliente", parameters);
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ritorno;
		
	}

	@Override
	public Object Execute(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
