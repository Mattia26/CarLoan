package business.entity;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.ClienteDao;
import entity.Cliente;

/**
 * Classe per la gestione di richieste da livello di business a livello di dao 
 * sulle operazioni relative ai clienti
* @author Giuseppe Onesto
* @author Mattia Menna
*/
public class ClienteBusiness {
	/**
	 * Attributo di classe, private di tipo ClienteDao.
	 */
	private static ClienteDao cliente;
	
	/**
	 * Costruttore: prova ad avvalorare l'attributo cliente, altrimenti genera un'eccezione 
	 * di tipo DatabaseInstantiationException
	 * @throws DatabaseInstantiationException
	 */
	public ClienteBusiness() throws DatabaseInstantiationException {
		try {
			cliente = DaoFactory.getDaoFactory(DaoFactory.MySQL).getClienteDao();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			// TODO Auto-generated catch block
			throw new DatabaseInstantiationException();
		}
	}
	
	/**
	 * Inserisce il cliente in input, tramite dao
	 * @param c di tipo Cliente: indica il cliente da inserire.
	 * @return true se il cliente è stato inserito, false altrimenti
	 */
	public boolean inserisciCliente(Cliente c){
		return cliente.inserisciCliente(c.getNome(), c.getCognome(), 
				c.getNumeroTelefono(), c.getCodFiscale());
	}

	/**
	 * Modifica il cliente in input, tramite dao
	 * @param c di tipo Cliente: indica il cliente da modificare.
	 * @return true se il cliente è stato modificato a livello dao, false altrimenti
	 */
	public boolean modificaCliente(Cliente c) {
		return cliente.modificaCliente(c.getCodFiscale(), c.getNumeroTelefono(),
				c.getNome(),c.getCognome());
	}

	/**
	 * Rimuove il cliente identificato dal codice fiscale in input, tramite dao
	 * @param cf di tipo String: indica il codice fiscale del cliente da rimuovere.
	 * @return true se il cliente è stato rimosso a livello dao, false altrimenti
	 */
	public boolean rimuoviCliente(String cf) {
		return cliente.rimuoviCliente(cf);
	}

	/**
	 * Prende l'insieme dei clienti presenti nel sistema al momento, tramite dao 
	 * @return ArrayList<Cliente>: insieme di clienti al momento
	 */
	public ArrayList<Cliente> getClienti() {
		return cliente.getClienti();
	}

}
