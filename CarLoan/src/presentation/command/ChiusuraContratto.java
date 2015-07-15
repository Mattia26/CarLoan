package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
import entity.Contratto;
/**
 * Classe che effettua la chiusura di un contratto
 * @author Mattia Menna
 *
 */
public class ChiusuraContratto implements Command{
	//attributo di tipo Business Delegato
	BusinessDelegate b;
	
	@Override
	public Object Execute(String parameter) {
		return false;
	}

	@Override
	public Object Execute(ArrayList<String> parameters) {
		boolean chiuso;
		try {
			b= new BusinessDelegate();
			chiuso = (boolean) b.handleRequest("ChiudiContratto", parameters);
			return chiuso;
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
