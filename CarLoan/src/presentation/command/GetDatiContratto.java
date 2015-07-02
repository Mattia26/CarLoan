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
			ritorno.add(InputController.dateToString(c.getDataInizio()));
			ritorno.add(InputController.dateToString(c.getDataFine()));
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
		/*ritorno.add("333333");
		ritorno.add("21/12/1999");
		ritorno.add("21/12/1999");
		ritorno.add("Milano");
		ritorno.add("Giornaliera");
		ritorno.add("Illimitato");
		ritorno.add("Giovanni");
		ritorno.add("Bianchi");
		ritorno.add("mnnmtt93r26f104d");
		ritorno.add("50");
		ritorno.add("333333333");*/
		return ritorno;
	}

}
