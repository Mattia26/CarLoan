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
	public boolean inserisciCliente(String nome, String cognome, int numTel, String CF){
		// TODO Auto-generated method stub
		return cliente.inserisciCliente(nome, cognome, numTel, CF);
	}

	public boolean modificaCliente(String codFiscale, int numTel) {
		// TODO Auto-generated method stub
		return cliente.modificaCliente(codFiscale, numTel);
	}

	public boolean rimuoviCliente(String codFiscale) {
		// TODO Auto-generated method stub
		return cliente.rimuoviCliente(codFiscale);
	}

	public ArrayList<Cliente> getClienti() {
		// TODO Auto-generated method stub
		return cliente.getClienti();
	}

}
