package presentation.command;

import java.util.ArrayList;

public class CalcolaSaldo implements Command{

	@Override
	public Object Execute(ArrayList<String> parameters) {
		double ritorno = 100.12;
		return ritorno;
	}

	@Override
	public Object Execute(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
