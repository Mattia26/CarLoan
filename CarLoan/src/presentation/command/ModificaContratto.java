package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.BusinessDelegate;

public class ModificaContratto implements Command{
			
		BusinessDelegate b;
			
			public Object Execute(String parameter){
				return null;
			}

			@Override
			public Object Execute(ArrayList<String> parameters) {
				boolean ritorno = false;
				b = new BusinessDelegate();
				
				try {
					ritorno = (boolean)b.handleRequest("ModificaContratto", parameters);
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
