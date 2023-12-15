package dataAccess;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class DataAccessHibernate {
	// private BLFacadeImplementation blfacadeImp;
	private BLFacade blfacade;

	private static DataAccessHibernate bdaInstance;

	public DataAccessHibernate() {
		try {
			DataAccess da = new DataAccess();
			blfacade = new BLFacadeImplementation(da);
			// blfacade.initializeBD();
		} catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear la Base de Datos.");
		}
		// blfacadeImp = new BLFacadeImplementation();
	}

	public static DataAccessHibernate getInstance() {
		if (bdaInstance == null) {
			bdaInstance = new DataAccessHibernate();
		}
		return bdaInstance;
	}

	public BLFacade getBLFAcade() {
		try {
			return this.blfacade;
		} catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear la Base de Datos.");
			return null;
		}
	}

	public void setBLFAcade(BLFacade blf) {
		try {
			this.blfacade = blf;
		} catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear la Base de Datos.");
		}
	}

}
