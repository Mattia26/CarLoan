package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
/**
 * Classe che si occupa della modifica dei dati di un cliente nel database
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class ModificaDatiCliente implements Command{

	private BusinessDelegate b;
	
	@Override
	public Object Execute(ArrayList<String> parameters) {
		
		boolean ritorno = false;
		b = new BusinessDelegate();
		
		try {
			ritorno = (boolean)b.handleRequest("ModificaDatiCliente", parameters);
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
