package business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Lookup implements LookupI{
	
	String classe,metodo;
	
	public Lookup(String request){
		switch(request){
		case "CercaAuto":
			classe = "business.GestisciAuto";
			metodo = "cercaAuto";
			
		}
		
	}

	@Override
	public Method FindService() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		Class<?> c = Class.forName(classe);
		
		//switch case per i vari metodi per passare in tipo di input corretto
		Method m = c.getDeclaredMethod(metodo,ArrayList.class);
		m.setAccessible(true);
		return m;
	}

}
