package business;

import java.util.ArrayList;
import java.util.Iterator;
import entity.Cliente;
import business.entity.ClienteBusiness;

public class GestisciCliente {
	ClienteBusiness cb;
	
	public Object inserisciCliente(ArrayList<String> parameters) {
		String nome = parameters.get(0);
		String cognome = parameters.get(1);
		int numTelefono = Integer.parseInt(parameters.get(2));
		String cf = parameters.get(3);
		Cliente c = new Cliente(nome, cognome, numTelefono, cf);
		return cb.inserisciCliente(c);
	}
	
	public Object modificaCliente(ArrayList<String> parameters) {
		String nome = parameters.get(0);
		String cognome = parameters.get(1);
		int numTelefono = Integer.parseInt(parameters.get(2));
		String cf = parameters.get(3);
		Cliente c = new Cliente(nome, cognome, numTelefono, cf);
		return cb.modificaCliente(c);
	}
	
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
