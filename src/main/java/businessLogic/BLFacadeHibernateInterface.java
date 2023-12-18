package businessLogic;

import java.util.Vector;
import java.util.Date;

//import domain.Booking;
import dominio.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
public interface BLFacadeHibernateInterface  {
	  

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
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date);
	

	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	
	
	public Vector<Question> getQuestions(Event event);
	
	/**
	 * Returns the questions associated to an Event
	 */	
	
	
	public boolean insertUser(String user, String pass);
}
