package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
/**
 * Classe che si occupa dell'inserimento di un nuovo contratto nel database
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class StipulaContratto implements Command{
	
	BusinessDelegate bd;
	
	@Override
	public Object Execute(String parameter){
		return null;
	}

	@Override
	public Object Execute(ArrayList<String> parameters) {
		bd = new BusinessDelegate();
		int id = -1;
		try {
			id = (int) bd.handleRequest("StipulaContratto", parameters);
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}
