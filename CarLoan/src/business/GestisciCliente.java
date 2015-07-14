package business;

import java.util.ArrayList;
import java.util.Iterator;

import entity.Cliente;
import business.entity.ClienteBusiness;
import business.entity.DatabaseInstantiationException;

public class GestisciCliente {
	/**
	 * Attributo privato di tipo ClienteBusiness
	 */
	ClienteBusiness cb;
	
	/**
	 * Costruttore: prova ad istanziare l'attributo cb; altrimenti lo setta a null
	 */
	public GestisciCliente() {
		try {
			cb = new ClienteBusiness();
		} catch (DatabaseInstantiationException e) {
			// TODO Auto-generated catch block
			cb = null;
		}
	}
	
	/**
	 * Metodo per l'inserimento di un cliente
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * l'inserimento del cliente
	 * @return true se il cliente è stato inserito correttamente; false altrimenti
	 */
	public Object inserisciCliente(ArrayList<String> parameters) {
		if(cb==null)
			return false;
		
		String nome = parameters.get(0);
		String cognome = parameters.get(1);
		String numTelefono = parameters.get(2);
		String cf = parameters.get(3);
		Cliente c = new Cliente(nome, cognome, numTelefono, cf);
		return cb.inserisciCliente(c);	
	}
	
	/**
	 * Metodo per la modifica dei dati di un cliente
	 * @param parameters: ArrayList<String> contenente i valori utili per 
	 * la modifica del cliente
	 * @return true se i dati del cliente sono stati modificati correttamente; false altrimenti
	 */
	public Object modificaCliente(ArrayList<String> parameters) {
		if(cb==null)
			return false;
				
		String nome = parameters.get(0); //non modificabile(?)
		String cognome = parameters.get(1); //non modificabile(?)
		String numTelefono = parameters.get(2);
		String cf = parameters.get(3); // non modificabile!
		
		Cliente c = new Cliente(nome, cognome, numTelefono, cf);
		return cb.modificaCliente(c);
		
	}
	
	
	/**
	 * Metodo che, a partire dalla stringa in input indicante il codice fiscale, 
	 * restituisce i dati del cliente associato a quel codice fiscale.
	 * @param parameter di tipo String: indica il codice fiscale del cliente.
	 * @return Cliente avente codice fiscale uguale alla stringa in input, se è presente
	 * nel sistema; null altrimenti.
	 * @throws ObjectNotFoundException
	 */
	public Object getDatiCliente(String CF) {
		if(cb==null)
			return null;
		
		ArrayList<Cliente> listaClienti = cb.getClienti();
		Iterator<Cliente> it = listaClienti.iterator();
		
		while(it.hasNext()) {
			Cliente c = it.next();
			if(c.getCodFiscale().toUpperCase().equals(CF) || c.getCodFiscale().toLowerCase().equals(CF))
				return c;
		}
		return null;
		
	}
	
}
