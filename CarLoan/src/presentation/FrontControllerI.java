package presentation;

import java.util.ArrayList;

public interface FrontControllerI {
	
	public Object handleRequest(String request);
	public Object handleRequest(String request,ArrayList<String> parameters);
	

}
