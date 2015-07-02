package business.entity;

import java.util.ArrayList;
import dao.DaoFactory;
import dao.ClienteDao;
import entity.Cliente;

public class ClienteBusiness {

	private static ClienteDao cliente;
	
	public ClienteBusiness() {
		try {
			cliente=DaoFactory.getDaoFactory(DaoFactory.MySQL).getClienteDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean inserisciCliente(Cliente c){
		// TODO Auto-generated method stub
		return cliente.inserisciCliente(c.getCognome(), c.getCognome(), 
				c.getNumeroTelefono(), c.getCodFiscale());
	}

	public boolean modificaCliente(Cliente c) {
		// TODO Auto-generated method stub
		return cliente.modificaCliente(c.getCodFiscale(), c.getNumeroTelefono());
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
