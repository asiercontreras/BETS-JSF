package businessLogic;
//hola
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import dataAccess.DataAccessHibernate;
import dataAccess.DataAccessHibernateInterface;
import dominio.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
//@WebService(endpointInterface = "businessLogic.BLFacade")
public final class BLFacadeHibernate  implements BLFacadeHibernateInterface {
	
	private DataAccessHibernateInterface dbManager;
	private static BLFacadeHibernate instance;
	
    private BLFacadeHibernate(DataAccessHibernateInterface da)  {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		this.dbManager = da;		
	}
    
    public static BLFacadeHibernate getInstance() {
    	if (instance == null) {
    		DataAccessHibernateInterface db = new DataAccessHibernate();
    		instance = new BLFacadeHibernate(db);
    	}
    	return instance;
    }
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   //@WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		//dbManager.open();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		//dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    //@WebMethod	
	public Vector<Event> getEvents(Date date)  {
		//dbManager.open();
		Vector<Event>  events=dbManager.getEvents(date);
		//dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	//@WebMethod 
	public Vector<Date> getEventsMonth(Date date) {
		//dbManager.open();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		//dbManager.close();
		return dates;
	}

	

}

