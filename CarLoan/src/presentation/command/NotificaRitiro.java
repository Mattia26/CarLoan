package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
/**
 * Classe per la notifica del ritiro di un auto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class NotificaRitiro implements Command{

	private BusinessDelegate b;
	
			public Object Execute(String parameter){
				boolean ritorno = false;
				
				b = new BusinessDelegate();
				try {
					ritorno = (boolean)b.handleRequest("NotificaRitiro",parameter );
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
