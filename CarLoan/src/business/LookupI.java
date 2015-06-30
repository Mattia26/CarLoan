package business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface LookupI {
	
	public Method FindService() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException ;

}
