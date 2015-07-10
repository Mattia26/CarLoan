package business.entity;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.ClienteDao;
import entity.Cliente;

public class ClienteBusiness {

	private static ClienteDao cliente;
	
	public ClienteBusiness() throws InstantiationException, IllegalAccessException {
		cliente=DaoFactory.getDaoFactory(DaoFactory.MySQL).getClienteDao();
	}
	
	public boolean inserisciCliente(Cliente c){
		// TODO Auto-generated method stub
		return cliente.inserisciCliente(c.getNome(), c.getCognome(), 
				c.getNumeroTelefono(), c.getCodFiscale());
	}

	public boolean modificaCliente(Cliente c) {
		// TODO Auto-generated method stub
		return cliente.modificaCliente(c.getCodFiscale(), c.getNumeroTelefono(),c.getNome(),c.getCognome());
	}

	public boolean rimuoviCliente(String cf) {
		// TODO Auto-generated method stub
		return cliente.rimuoviCliente(cf);
	}

	public ArrayList<Cliente> getClienti() {
		// TODO Auto-generated method stub
		return cliente.getClienti();
	}

}
