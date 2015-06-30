package business.entity;

import java.util.ArrayList;
import java.util.HashMap;

import dao.DaoFactory;
import dao.ClienteDao;

public abstract class Cliente {

	private static ClienteDao cliente;
	
	Cliente() {
		try {
			cliente=DaoFactory.getDaoFactory(DaoFactory.MySQL).getClienteDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean inserisciCliente(String nome, String cognome, 
			int numTel,String codFiscale) {
		// TODO Auto-generated method stub
		return cliente.inserisciCliente(nome, cognome, numTel,codFiscale);
	}

	public static boolean modificaCliente(String codFiscale, int numTel) {
		// TODO Auto-generated method stub
		return cliente.modificaCliente(codFiscale, numTel);
	}

	public static boolean rimuoviCliente(String codFiscale) {
		// TODO Auto-generated method stub
		return cliente.rimuoviCliente(codFiscale);
	}

	public static ArrayList<HashMap<String, String>> getClienti() {
		// TODO Auto-generated method stub
		return cliente.getClienti();
	}

}
