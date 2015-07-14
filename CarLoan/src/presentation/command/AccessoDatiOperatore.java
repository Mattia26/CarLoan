package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
import entity.Operatore;
/**
 * Classe per il recupero dei dati di un operatore e la formattazione 
 * per la visualizzazione in interfaccia dei sudetti dati
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class AccessoDatiOperatore implements Command{
	
	BusinessDelegate b;
	
	public Object Execute(String parameter){
		ArrayList<String> ritorno = new ArrayList<String>();
		Operatore o;
		b  = new BusinessDelegate();
				
		try {
			o = (Operatore)b.handleRequest("AccessoDatiOperatore", parameter);
					
			ritorno.add(o.getNome());
			ritorno.add(o.getCognome());
			ritorno.add(o.getIndirizzo());
			ritorno.add(o.getNumTelefono());
			ritorno.add(o.getNickname());
		} 
		catch (ClassNotFoundException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| InstantiationException | NoSuchMethodException
					| SecurityException | NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ArrayList<String>();
				}
				
				
				return ritorno;
				
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				// TODO Auto-generated method stub
				return null;
			}

}