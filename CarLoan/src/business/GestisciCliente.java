package business;

import java.util.ArrayList;
import java.util.Iterator;

import entity.Cliente;
import business.entity.ClienteBusiness;
import business.entity.DatabaseInstantiationException;

public class GestisciCliente {
	ClienteBusiness cb;
	
	public GestisciCliente() {
		try {
			cb = new ClienteBusiness();
		} catch (DatabaseInstantiationException e) {
			// TODO Auto-generated catch block
			cb = null;
		}
	}
	
	public Object inserisciCliente(ArrayList<String> parameters) {

		try {
			String nome = parameters.get(0);
			String cognome = parameters.get(1);
			String numTelefono = parameters.get(2);
			String cf = parameters.get(3);
			Cliente c = new Cliente(nome, cognome, numTelefono, cf);
			return cb.inserisciCliente(c);
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	public Object modificaCliente(ArrayList<String> parameters) {
		
		try {		
			String nome = parameters.get(0); //non modificabile(?)
			String cognome = parameters.get(1); //non modificabile(?)
			String numTelefono = parameters.get(2);
			String cf = parameters.get(3); // non modificabile!
			Cliente c = new Cliente(nome, cognome, numTelefono, cf);
			return cb.modificaCliente(c);
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	public Object getDatiCliente(String CF) throws ObjectNotFoundException {
		
		try {
		ArrayList<Cliente> listaClienti = cb.getClienti();
		Iterator<Cliente> it = listaClienti.iterator();
		
		while(it.hasNext()) {
			Cliente c = it.next();
			if(c.getCodFiscale().toUpperCase().equals(CF) || c.getCodFiscale().toLowerCase().equals(CF))
				return c;
		}
		return null;
		}
		catch(NullPointerException e) {
			return null;
		}
	} 
}
