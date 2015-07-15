package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
import presentation.GestioneSessione;
/**
 * Classe che si occupa della modifica dei dati di un operatore nel database
 * @author Mattia Menna
 * @author Giuseppe Onesto
 */
public class ModificaDatiOperatore implements Command{
	
	BusinessDelegate b;
	
			public Object Execute(String parameter){
				return null;
				
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				boolean ritorno=false;
				b = new BusinessDelegate();
				
				try {
					ritorno = (boolean)b.handleRequest("ModificaDatiOperatore", parameters);
					if(ritorno) {
						GestioneSessione.setNomeOperatore(parameters.get(0));
						GestioneSessione.setCognomeOperatore(parameters.get(1));
						GestioneSessione.setTelefonoOperatore(parameters.get(2));
						GestioneSessione.setIndirizzoOperatore(parameters.get(3));
					}
				} catch (ClassNotFoundException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException
						| InstantiationException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return ritorno;
			}

}
