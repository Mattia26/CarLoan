package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import entity.Auto;
import business.BusinessDelegate;
/**
 * Classe che recupera i dati relativi a una data auto
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class CercaAuto implements Command{
			
			BusinessDelegate b;	
			
			public Object Execute(String parameter){
				return null;
				
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				b = new BusinessDelegate();
				ArrayList<Auto> auto;
				ArrayList<String> ritorno = null;
				
				
					try {
						ritorno = new ArrayList<String>();
						auto = (ArrayList<Auto>) b.handleRequest("CercaAuto", parameters);
						Iterator<Auto> it = auto.iterator();
						while(it.hasNext()){
							Auto current = it.next();
							String automobile = current.getTarga() + "  " + current.getModello() + 
									"  " + current.getFascia()+ "  " + current.getUltimoChilometraggio();
							ritorno.add(automobile);
						}
					} catch (ClassNotFoundException | NoSuchMethodException
							| SecurityException | IllegalAccessException
							| IllegalArgumentException
							| InvocationTargetException
							| InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				return ritorno;
			}

}
