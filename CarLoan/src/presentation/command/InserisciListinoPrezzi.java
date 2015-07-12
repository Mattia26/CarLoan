package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;

public class InserisciListinoPrezzi implements Command {

	private BusinessDelegate b;
	
	@Override
	public Object Execute(ArrayList<String> parameters) {
		boolean ritorno = false;
		b = new BusinessDelegate();
		
		try {
			ritorno = (boolean)b.handleRequest("InserisciListino", parameters);
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
