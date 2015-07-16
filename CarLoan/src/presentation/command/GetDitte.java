package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;

/**
 * Classe che restituisce le ditte presenti nel sistema
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class GetDitte implements Command{

	@Override
	public Object Execute(ArrayList<String> parameters) {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}

	@Override
	public Object Execute(String parameter) {
		// TODO Auto-generated method stub
		ArrayList<String> città;
		BusinessDelegate b = new BusinessDelegate();
		try {
			città = (ArrayList<String>) b.handleRequest("GetDitte", parameter);
			return città;
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			return new ArrayList<String>();
		}
	}

}
