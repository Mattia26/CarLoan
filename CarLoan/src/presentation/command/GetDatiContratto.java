package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import presentation.GestioneSessione;
import utility.InputController;
import entity.Cliente;
import entity.Contratto;
import business.BusinessDelegate;
import business.ObjectNotFoundException;
/**
 * Classe che pone i dati di un dato contratto in GestioneSessione
 * @author mattia
 *
 */
public class GetDatiContratto implements Command{
	
	BusinessDelegate b;

	@Override
	public Object Execute(ArrayList<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Execute(String parameter) {
		ArrayList<String> ritorno = new ArrayList<String>();
		b = new BusinessDelegate();
		Contratto c;
		Cliente client;
		try {
			c = (Contratto)b.handleRequest("GetDatiContratto", parameter);
			try {
				client = (Cliente)b.handleRequest("AccessoDatiCliente", c.getCliente());
				
				GestioneSessione.setTarga(c.getTargaMacchina());
				GestioneSessione.setAcconto(c.getQuotaAcconto());
				GestioneSessione.setTelefonoCliente(client.getNumeroTelefono());
				GestioneSessione.setSedeRestituzione(c.sedeRestituzione());
				GestioneSessione.setTipoContratto(c.getTipologia());
				GestioneSessione.setTipoKmContratto(c.getTipoChilometraggio());
				
				ritorno.add(c.getTargaMacchina());
				ritorno.add(c.getDataInizio());
				ritorno.add(c.getDataFine());
				ritorno.add(c.sedeRestituzione());
				if(c.getTipologia() == 'G')
					ritorno.add("Giornaliera");
				else
					ritorno.add("Settimanale");
				
				if(c.getTipoChilometraggio() == 'I')
					ritorno.add("Illimitato");
				else
					ritorno.add("Limitato");
				
				ritorno.add(client.getNome());
				ritorno.add(client.getCognome());
				ritorno.add(client.getCodFiscale());
				ritorno.add(String.valueOf(c.getQuotaAcconto()));
				ritorno.add(client.getNumeroTelefono());
			}
			catch (ClassNotFoundException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException 
					| InstantiationException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				return new ArrayList<String>(); //DA GESTIRE L'ECCEZIONE DI CLIENTE CON CF GIA' INSERITO MA CON NOME E/O COGNOME E/O NUM TELEFONO DIVERSI DA QUELLI DEL DB!!! CAZZO!!!
			}
		}
		catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | InstantiationException 
				| NoSuchMethodException | SecurityException | NullPointerException  e) {
				return new ArrayList<String>(); // TODO Auto-generated catch block
	
		}
		
		
		
		return ritorno;
	}

}
