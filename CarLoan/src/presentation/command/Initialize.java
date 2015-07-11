package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Contratto;
import business.BusinessDelegate;

public class Initialize implements Command{
	
	BusinessDelegate b;

	@Override
	public Object Execute(ArrayList<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Execute(String parameter) {
		b = new BusinessDelegate();
		ArrayList<Contratto> contratti = new ArrayList<Contratto>();
		String ritorno = "";
		
		try {
			contratti = (ArrayList<Contratto>)b.handleRequest("Initialize", parameter);
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!contratti.isEmpty()){
			ritorno = "I seguenti contratti non stati chiusi e l'auto non è stata restituita:\n\n";
			Iterator<Contratto> it = contratti.iterator();
			int i = 0;
			while(it.hasNext()){
				i +=1;
				Contratto current = it.next();
				
				ritorno += Integer.toString(i) + 
						"- Id contratto: " + Integer.toString(current.getId()) + "\n" +
							"    Cliente: " + current.getCliente() + "\n\n";
			}
		}
		
		return ritorno;
	}

}
