package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import business.BusinessDelegate;
import entity.Contratto;
/**
 * Classe che restituisce i dati relativi a un dato contratto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class CercaContratto implements Command{

	BusinessDelegate b;
	
	@Override
	public Object Execute(String parameter){
		Contratto c;
		try {
			b= new BusinessDelegate();
			c = (Contratto) b.handleRequest("CercaContratto", parameter);
			return c.toString();
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object Execute(ArrayList<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
