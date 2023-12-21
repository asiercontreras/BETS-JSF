package dataAccess;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dominio.*;
import exceptions.BetAlreadyExist;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;

public interface DataAccessHibernateInterface {
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	void initializeDB();

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist;

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	List<Event> getEvents(Date date);

	

	
	/**
	 * This method checks if the question has been previously added to the event 
	 * 
	 * @param event the event
	 * @param question the question to check  
	 * @return true if the event contains this the questions, false in other case
	 */
	boolean existQuestion(Event event, String question);
	
	
	
	
	List<Question> getQuestions(Event e);
	
	
	public boolean insertUser(String user, String pass, String salt,String nombre, String apellido, Date date);
	
	public User getUser(String user);
	
	public Bet createBet(Question question, String descripton, float minBet, User usr) throws BetAlreadyExist;
	
	public boolean existBet(Question question, String descripton, float minBet);
		
	

}