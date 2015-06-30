package business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class BusinessDelegate {
	
	private LookupI look;
	
	public Object handleRequest(String request,Object parameters) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		look=new Lookup(request);
		Method m=look.FindService();
		return m.invoke(m.getDeclaringClass().newInstance(), parameters);
	}
	

}
