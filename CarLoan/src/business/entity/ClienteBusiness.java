package business.entity;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.ClienteDao;
import entity.Cliente;

public class ClienteBusiness {

	private static ClienteDao cliente;
	
	public ClienteBusiness() throws DatabaseInstantiationException {
		try {
			cliente = DaoFactory.getDaoFactory(DaoFactory.MySQL).getClienteDao();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			// TODO Auto-generated catch block
			throw new DatabaseInstantiationException();
		}
	}
	
	public boolean inserisciCliente(Cliente c){
		return cliente.inserisciCliente(c.getNome(), c.getCognome(), 
				c.getNumeroTelefono(), c.getCodFiscale());
	}

	public boolean modificaCliente(Cliente c) {
		return cliente.modificaCliente(c.getCodFiscale(), c.getNumeroTelefono(),c.getNome(),c.getCognome());
	}

	public boolean rimuoviCliente(String cf) {
		return cliente.rimuoviCliente(cf);
	}

	public ArrayList<Cliente> getClienti() {
		return cliente.getClienti();
	}

}
