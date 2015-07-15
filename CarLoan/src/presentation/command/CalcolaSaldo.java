package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
/**
 * Classe per il calcolo del saldo di un contratto concluso
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class CalcolaSaldo implements Command{
	
	BusinessDelegate b;

	@Override
	public Object Execute(ArrayList<String> parameters) {
		double ritorno = -1;
		b = new BusinessDelegate();
		
		try {
			ritorno = (double)b.handleRequest("CalcolaSaldo", parameters);
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
