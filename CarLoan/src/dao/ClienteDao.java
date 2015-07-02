package dao;

import java.util.ArrayList;
import entity.Cliente;

public interface ClienteDao {
	public boolean inserisciCliente(String nome, String cognome, String numTel, String codFiscale);
	public boolean modificaCliente(String codFiscale, String numTel);
	public boolean rimuoviCliente(String codFiscale);
	public ArrayList<Cliente> getClienti();
}
