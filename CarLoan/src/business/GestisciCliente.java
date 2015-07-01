package business;

import java.util.ArrayList;
import java.util.Iterator;
import entity.Cliente;
import business.entity.ClienteBusiness;

public class GestisciCliente {
	ClienteBusiness cb;
	
	public Object getDatiCliente(String CF) {
		cb=new ClienteBusiness();
		ArrayList<Cliente> listaClienti = cb.getClienti();
		Iterator<Cliente> it = listaClienti.iterator();
		
		while(it.hasNext()) {
			Cliente c = it.next();
			if(c.getCodFiscale().equals(CF))
				return c;
		}
		return null;
	} 
}
