package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;
import entity.Cliente;

public class GetDatiCliente implements Command{
		
		private BusinessDelegate b;
	
	
			public Object Execute(String parameter){
				ArrayList<String> ritorno = new ArrayList<String>();
				Cliente c;
				b  = new BusinessDelegate();
				
				try {
					c = (Cliente)b.handleRequest("AccessoDatiCliente", parameter);
					
					ritorno.add(c.getNome());
					ritorno.add(c.getCognome());
					ritorno.add(c.getNumeroTelefono());
				} catch (ClassNotFoundException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException
						| InstantiationException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return ritorno;
				
			}

			public Object Execute(ArrayList<String> parameters) {
				return null;
			}

}
