package presentation.command;

import java.util.ArrayList;

public interface Command {
	 
	public Object Execute(ArrayList<String> parameters);
	public Object Execute(String parameter);
	
}
