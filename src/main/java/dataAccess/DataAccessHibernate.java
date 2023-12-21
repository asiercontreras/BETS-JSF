package dataAccess;

//hello
import java.util.Calendar;
import java.util.Date;

import java.util.List;

import org.hibernate.*;

import configuration.UtilDate;
import dominio.*;
import exceptions.BetAlreadyExist;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessHibernate implements DataAccessHibernateInterface {

	public DataAccessHibernate() {
		this.initializeDB(); // Esto hay que cambiar si no se quiere sobreescribir
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
			q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
			q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
			q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
			q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
			q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);

			session.save(ev1);
			session.save(ev2);
			session.save(ev3);
			session.save(ev4);
			session.save(ev5);
			session.save(ev6);
			session.save(ev7);
			session.save(ev8);
			session.save(ev9);
			session.save(ev10);
			session.save(ev11);
			session.save(ev12);
			session.save(ev13);
			session.save(ev14);
			session.save(ev15);
			session.save(ev16);
			session.save(ev17);
			session.save(ev18);
			session.save(ev19);
			session.save(ev20);

			session.save(q1);
			session.save(q2);
			session.save(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);

			session.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Query query = session.createQuery("FROM Event WHERE eventNumber = :number");
		query.setParameter("number", event.getEventNumber());
		Event myEvent = (Event) query.uniqueResult();
		if (myEvent.DoesQuestionExists(question)) {

			System.out.println("Ya existe una pregunta con ese nombre");

			throw new QuestionAlreadyExist("Ya existe una pregunta con ese nombre");
		}

		Question q = myEvent.addQuestion(question, betMinimum);
		// session.save(q);
		session.persist(myEvent);
		session.getTransaction().commit();

		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		System.out.println(">> DataAccess: getEvents");

		Query query = session.createQuery("FROM Event WHERE eventDate = :date");
		query.setParameter("date", date);
		List<Event> listEvents = query.list(); // Suponiendo que query.list() devuelve una lista
		// Set<Event> events = new HashSet<Event>(listEvents); // Convertir la lista a
		// un Set utilizando HashSet

		session.getTransaction().commit();

		return listEvents;
	}

	public boolean existQuestion(Event event, String question) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);

		Query query = session.createQuery("FROM Event WHERE eventNumber = :number");
		query.setParameter("number", event.getEventNumber());
		Event myEvent = (Event) query.uniqueResult();

		return myEvent.DoesQuestionExists(question);

	}

	/**
	 * Returns a vector with the questions
	 */
	@Override
	public List<Question> getQuestions(Event e) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(">> DataAccess: getQuestions");
		Query query = session.createQuery("FROM Question WHERE event = :e");
		query.setParameter("e", e);
		List<Question> listQuestions = query.list(); // Suponiendo que query.list() devuelve una lista
		session.getTransaction().commit();
		return listQuestions;
	}

	/**
	 * Inserta un usuario nuevo en la base de datos. Método creado para register.
	 * 
	 * @return
	 */
	public boolean insertUser(String user, String pass, String salt, String nombre, String apellido, Date date) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			User myUser = new User(user, pass, salt, nombre, apellido, date);
			session.save(myUser);

			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (e instanceof org.hibernate.NonUniqueObjectException) {
				System.out.println("Se ha intentado insertar un usuario con el mismo username.");
			}
			return false;
		}

	}

	public User getUser(String user) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session.createQuery("FROM User WHERE username = :usern");
			query.setParameter("usern", user);
			User myUser = (User) query.uniqueResult();

			return myUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Bet createBet(Question question, String descripton, float minBet) throws BetAlreadyExist {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(">> DataAccess: createBet => Question: " + question + " Description: " + descripton
				+ " MinBet: " + minBet);
		
		Query query = session.createQuery("FROM Question WHERE questionNumber = :q");
		query.setParameter("q", question.getQuestionNumber());
		
		Question quest = (Question) query.uniqueResult();
		
		if(quest.doesBetExist(descripton)) {
			System.out.println("Bet already exist");
			throw new BetAlreadyExist("Sorry but the bet already exist");
		}
		
		Bet bet = quest.addBet(descripton, minBet);
		
		//session.save(bet);
		session.persist(quest);
		
		session.getTransaction().commit();
		
		return bet;
	}
	
	public boolean existBet(Question question, String descripton, float minBet) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(">> DataAccess: existBet");
		
		Query query = session.createQuery("FROM Question WHERE questionNumber = :q");
		query.setParameter("q", question.getQuestionNumber());
		
		Question quest = (Question) query.uniqueResult();
		
		return quest.doesBetExist(descripton);
	}

}
