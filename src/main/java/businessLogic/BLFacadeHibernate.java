package businessLogic;

//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import dataAccess.DataAccessHibernate;
import dataAccess.DataAccessHibernateInterface;
import dominio.*;
import exceptions.*;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * It implements the business logic as a web service.
 */
//@WebService(endpointInterface = "businessLogic.BLFacade")
public final class BLFacadeHibernate implements BLFacadeHibernateInterface {

	private DataAccessHibernateInterface dbManager;
	private static BLFacadeHibernate instance;

	private BLFacadeHibernate(DataAccessHibernateInterface da) {
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
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	// @WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		// dbManager.open();
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		// dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	// @WebMethod
	public List<Event> getEvents(Date date) {

		return dbManager.getEvents(date);
	}

	public List<Question> getQuestions(Event event) {
		return dbManager.getQuestions(event);
	}

	public boolean insertUser(String user, String pass, String nombre, String apellido, Date date) {

		// Un salt es pequeño valor aleatorio
		String salt = BCrypt.gensalt();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// Se concatena el salt (valor aleatorio) con la contraseña y se encodifica con
		// la librería crypto de spring
		String hashedPassword = encoder.encode(salt + pass);

		/**
		 * Lo interesante es que ahora tenemos una contraseña hasheada y nuestro salt
		 * Guardaremos ambos en la base de datos, y cuando necesitemos comparar la
		 * contraseña para el login, por ejemplo, repetimos la operación anterior con la
		 * contraseña introducida y compararemos los valores.
		 * 
		 * Esta es la manera (o una de las maneras) más modernas de almacenar
		 * contraseñas
		 */

		return dbManager.insertUser(user, hashedPassword, salt, nombre, apellido, date);

	}

	public boolean checkUserPass(String user, String pass) {
		User myUser;
		if ((myUser = dbManager.getUser(user)) == null) {
			System.out.println("No se ha encontrado user");
			return false;
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// Este método nunca decodifica la contraseña, lo unico que hace es comparar
		return encoder.matches(myUser.getSalt() + pass, myUser.getPassword());
	}
	public Bet createBet(Question question, String descripton, float minBet) throws BetAlreadyExist{
		Bet bet = dbManager.createBet(question, descripton, minBet);
		return bet;
	}

}
