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
			break;
		case "CercaAuto":
			classe = "business.GestisciAuto";
			metodo = "cercaAuto";
			input = ArrayList.class;
			break;
		case "CercaContratto":
			classe = "business.GestisciContratto";
			metodo = "cercaContratto";
			input = String.class;
			break;
		case "ChiudiContratto":
			classe = "business.GestisciContratto";
			metodo = "chiudiContratto";
			input = String.class;
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
		case "InserisciAuto":
			classe = "business.GestisciAuto";
			metodo = "inserisciAuto";
			input = ArrayList.class;
			break;
		case "InserisciInManutenzione":
			classe = "business.GestisciAuto";
			metodo = "inserisciInManutenzione";
			input = String.class;
			break;
		case "InserisciOperatore":
			classe = "business.GestisciOperatore";
			metodo = "inserisciNuovoOperatore";
			input = ArrayList.class;
			break;
		case "ModificaContratto":
			classe = "business.GestisciContratto";
			metodo = "modificaContratto";
			input = ArrayList.class;
			break;
		case "ModificaCliente":
			classe = "business.GestisciCliente";
			metodo = "modificaCliente";
			input = ArrayList.class;
			break;
		case "ModificaDatiOperatore":
			classe = "business.GestisciOperatore";
			metodo = "modificaDatiOperatore";
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
