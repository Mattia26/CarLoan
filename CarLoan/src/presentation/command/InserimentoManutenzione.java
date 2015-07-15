package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
/**
 * Classe che si occupa dell'inserimento di un auto in manutenzione
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class InserimentoManutenzione implements Command{

		private BusinessDelegate b;
	
			public Object Execute(String parameter){
				
				boolean ritorno = false;
				b = new BusinessDelegate();

				try {
					ritorno = (boolean)b.handleRequest("InserisciManutenzione", parameter);
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
			public Object Execute(ArrayList<String> parameters) {
				// TODO Auto-generated method stub
				return null;
			}

}
