package presentation.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import presentation.GestioneSessione;
import utility.InputController;
import entity.Cliente;
import entity.Contratto;
import entity.ListinoPrezzi;
import business.BusinessDelegate;
import business.ObjectNotFoundException;

/**
 * Classe che pone i prezzi attualmente in vigore in GestioneSessione e li inserisce
 * in un ArrayList che verrà restituito alle classi che richiamano il metodo Execute
 * @author Giuseppe
 *
 */
public class GetPrezziAttuali implements Command{
	
	BusinessDelegate b;

	@Override
	public Object Execute(ArrayList<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Execute(String parameter) {
		ArrayList<Integer> ritorno = new ArrayList<Integer>();
		b = new BusinessDelegate();
		ListinoPrezzi l;
		
		try {
			l = (ListinoPrezzi)b.handleRequest("GetPrezziListino", parameter);
		
			GestioneSessione.setCostoGiornaliero(l.getCostoGiornaliero());
			GestioneSessione.setCostoSettimanale(l.getCostoSettimanale());
			GestioneSessione.setCostoKmLimitato(l.getCostoKmLimitato());
			GestioneSessione.setCostoKmIllimitato(l.getCostoKmIllimitato());
				
			ritorno.add(l.getCostoGiornaliero());
			ritorno.add(l.getCostoSettimanale());
			ritorno.add(l.getCostoKmLimitato());
			ritorno.add(l.getCostoKmIllimitato());
		
		}
		catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | InstantiationException 
				| NoSuchMethodException | SecurityException | NullPointerException  e) {
				return new ArrayList<Integer>(); // TODO Auto-generated catch block
	
		}
		
		return ritorno;
	}

}

