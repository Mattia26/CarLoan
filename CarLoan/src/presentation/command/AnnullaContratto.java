package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;

public class AnnullaContratto implements Command{

	BusinessDelegate bd;
	
	public Object Execute(String parameter){
		bd = new BusinessDelegate();
		boolean annulla=false;
		try {
			annulla = (boolean) bd.handleRequest("AnnullaContratto", parameter);
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return annulla;		
	}

	@Override
	public Object Execute(ArrayList<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
