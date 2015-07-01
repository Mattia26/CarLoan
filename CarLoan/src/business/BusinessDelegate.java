package business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BusinessDelegate {
	
	private LookupI look;
	
	public Object handleRequest(String request,Object parameters) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, InstantiationException, 
			NoSuchMethodException, SecurityException {
		
		look=new Lookup(request);
		Method m=look.FindService();
		return m.invoke(m.getDeclaringClass().newInstance(), parameters);
	}
}
