package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import presentation.GestioneSessione;
import business.BusinessDelegate;
import business.ObjectNotFoundException;
import entity.Cliente;

public class GetDatiCliente implements Command{
		
		private BusinessDelegate b;
	
	
			public Object Execute(String parameter){
				ArrayList<String> ritorno = new ArrayList<String>();
				Cliente c;
				b  = new BusinessDelegate();
				
				try {
					c = (Cliente)b.handleRequest("AccessoDatiCliente", parameter);
					
					GestioneSessione.setCFCliente(c.getCodFiscale());
					GestioneSessione.setNomeCliente(c.getNome());
					GestioneSessione.setCognomeCliente(c.getCognome());
					GestioneSessione.setTelefonoCliente(c.getNumeroTelefono());
					
					ritorno.add(c.getNome());
					ritorno.add(c.getCognome());
					ritorno.add(c.getNumeroTelefono());
				} catch (ClassNotFoundException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException
						| InstantiationException | NoSuchMethodException
						| SecurityException | NullPointerException e) {
					// TODO Auto-generated catch block
					return new ArrayList<String>();
				}
				
				
				return ritorno;
				
			}

			public Object Execute(ArrayList<String> parameters) {
				return null;
			}

}
