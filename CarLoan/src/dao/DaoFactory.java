package dao;

public abstract class DaoFactory {

	public static final int MySQL=1;
	
	public abstract AutoDao getAutoDao() throws InstantiationException, IllegalAccessException;
	public abstract ContrattoDao getContrattoDao() throws InstantiationException, IllegalAccessException;
	public abstract ClienteDao getClienteDao() throws InstantiationException, IllegalAccessException;
	public abstract OperatoreDao getOperatoreDao() throws InstantiationException, IllegalAccessException;


public static DaoFactory getDaoFactory(int i) {
	DaoFactory dao;
	switch (i) {
	case MySQL:
		dao = new MySQLDaoFactory();
		break;
	default:
		dao = null;
		break;
		}
	return dao;
	}

protected static Object createDao(Class c) 
		throws InstantiationException, IllegalAccessException {
return c.newInstance();
	}
}