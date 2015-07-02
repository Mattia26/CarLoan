package presentation.command;

import java.util.ArrayList;

public class GetDatiContratto implements Command{

	@Override
	public Object Execute(ArrayList<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Execute(String parameter) {
		ArrayList<String> ritorno = new ArrayList<String>();
		ritorno.add("333333");
		ritorno.add("21/12/1999");
		ritorno.add("21/12/1999");
		ritorno.add("Milano");
		ritorno.add("Giornaliera");
		ritorno.add("Illimitato");
		ritorno.add("Giovanni");
		ritorno.add("Bianchi");
		ritorno.add("mnnmtt93r26f104d");
		ritorno.add("50");
		ritorno.add("333333333");
		return ritorno;
	}

}
