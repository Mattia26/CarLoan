package business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Lookup implements LookupI{
	
	String classe,metodo;
	Class<?> input;
	
	public Lookup(String request){
		switch(request){
		case "AccessoDatiCliente":
			classe = "business.GestisciCliente";
			metodo = "getDatiCliente";
			input = String.class;
			break;
		case "AccessoDatiOperatore":
			classe = "business.GestisciOperatore";
			metodo = "getDatiOperatore";
			input = String.class;
			break;
		case "AnnullaContratto":
			classe = "business.GestisciContratto";
			metodo = "annullaContratto";
			input = String.class;
			break;
		case "CalcolaSaldo":
			classe = "business.GestisciContratto";
			metodo = "calcolaImporto";
			input = ArrayList.class;
			break;
		case "CercaAuto":
			classe = "business.GestisciAuto";
			metodo = "cercaAuto";
			input = ArrayList.class;
			break;
		case "ChiudiContratto":
			classe = "business.GestisciContratto";
			metodo = "chiudiContratto";
			input = ArrayList.class;
			break;
		case "EliminaAuto":
			classe = "business.GestisciAuto";
			metodo = "eliminaAuto";
			input = String.class;
			break;
		case "EliminaOperatore":
			classe = "business.GestisciOperatore";
			metodo = "eliminaOperatore";
			input = String.class;
			break;
		case "GetDatiContratto":
			classe = "business.GestisciContratto";
			metodo = "getDatiContratto";
			input = String.class;
			break;
		case "InserisciAuto":
			classe = "business.GestisciAuto";
			metodo = "inserisciAuto";
			input = ArrayList.class;
			break;
		case "InserisciCliente":
			classe = "business.GestisciCliente";
			metodo = "inserisciCliente";
			input = ArrayList.class;
			break;
		case "InserisciManutenzione":
			classe = "business.GestisciAuto";
			metodo = "inserisciInManutenzione";
			input = String.class;
			break;
		case "InserisciOperatore":
			classe = "business.GestisciOperatore";
			metodo = "inserisciOperatore";
			input = ArrayList.class;
			break;
		case "ModificaAuto":
			classe = "business.GestisciAuto";
			metodo = "modificaAuto";
			input = ArrayList.class;
			break;
		case "ModificaContratto":
			classe = "business.GestisciContratto";
			metodo = "modificaContratto";
			input = ArrayList.class;
			break;
		case "ModificaDatiCliente":
			classe = "business.GestisciCliente";
			metodo = "modificaCliente";
			input = ArrayList.class;
			break;
		case "ModificaDatiOperatore":
			classe = "business.GestisciOperatore";
			metodo = "modificaOperatore";
			input = ArrayList.class;
			break;
		case "NotificaRitiro":
			classe = "business.GestisciContratto";
			metodo = "notificaRitiroAuto";
			input = String.class;
			break;
		case "StipulaContratto":
			classe = "business.GestisciContratto";
			metodo = "nuovoContratto";
			input = ArrayList.class;
			break;
		case "Initialize":
			classe = "business.GestisciContratto";
			metodo = "Initialize";
			input = String.class;
			break;
		case "ModificaListino":
			classe = "business.entity.ListinoBusiness";
			metodo = "modificaPrezzi";
			input = ArrayList.class;
			break;
			
		}
		
	}

	@Override
	public Method FindService() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		Class<?> c = Class.forName(classe);
		Method m = c.getDeclaredMethod(metodo,input);
		m.setAccessible(true);
		return m;
	}

}	