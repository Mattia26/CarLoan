package presentation;

import java.util.ArrayList;
/**
 * Classe che riceve e smista tutte le richieste dalle interfacce
 * 
 * @author Mattia Menna
 * @author Giuseppe Onesto
 * @see presentation.FronControllerI
 */
public class FrontController implements FrontControllerI{
	/**
	 * Attributo che venendo istanziato con un Application Controller
	 * che lo implementa riceverà tutte le richieste
	 */
	ApplicationControllerI controller;
	
	
	public Object handleRequest(String request){
		controller = new ApplicationController();
		
		return controller.handleRequest(request);
	}
	
	
	public Object handleRequest(String request,ArrayList<String> parameters){
		controller = new ApplicationController();
		
		return controller.handleRequest(request,parameters);
	}

}
