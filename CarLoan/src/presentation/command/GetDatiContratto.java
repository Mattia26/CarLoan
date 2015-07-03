package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import utility.InputController;
import entity.Cliente;
import entity.Contratto;
import business.BusinessDelegate;

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
			client = (Cliente)b.handleRequest("AccessoDatiCliente", c.getCliente());
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
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ritorno;
	}

}
