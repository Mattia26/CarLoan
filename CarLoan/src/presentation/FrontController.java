package presentation;

import java.util.ArrayList;

public class FrontController implements FrontControllerI{
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
