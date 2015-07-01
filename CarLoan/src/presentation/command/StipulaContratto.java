package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;

public class StipulaContratto implements Command{
	
	BusinessDelegate bd;
	
	@Override
	public Object Execute(String parameter){
		return null;
	}

	@Override
	public Object Execute(ArrayList<String> parameters) {
		bd = new BusinessDelegate();
		boolean stipulato = false;
		try {
			stipulato = (boolean) bd.handleRequest("StipulaContratto", parameters);
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stipulato;
	}
}
