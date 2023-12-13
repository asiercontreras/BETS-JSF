package Bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class BeanDataAccess {
	//private BLFacadeImplementation blfacadeImp;
	private BLFacade blfacade;
	
	private static BeanDataAccess bdaInstance;
	
	public BeanDataAccess() {
		try {
			DataAccess da = new DataAccess();
			blfacade = new BLFacadeImplementation(da);
			//blfacade.initializeBD();
		}
		catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear la Base de Datos.");
		}
		//blfacadeImp = new BLFacadeImplementation();
	}
	
	public static BeanDataAccess getInstance() {
		if(bdaInstance == null) {
			bdaInstance = new BeanDataAccess();
		}
		return bdaInstance;
	}
	
	public BLFacade getBLFAcade() {
		try {
			return this.blfacade;
		}
		catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear la Base de Datos.");
			return null;
		}
	}
	
	public void setBLFAcade(BLFacade blf) {
		try {
			this.blfacade = blf;
		}
		catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear la Base de Datos.");
		}
	}
	
}
