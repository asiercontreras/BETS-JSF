package Bean;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacadeHibernate;
import businessLogic.BLFacadeHibernateInterface;
import dominio.*;

public class QueryQuestionBean {
	private Date date;
	private Event event;
	private List<Event> events;
	private Question question;
	private List<Question> questions;
	private BLFacadeHibernateInterface bl;

	public QueryQuestionBean() {
		bl = BLFacadeHibernate.getInstance();
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * @param  the events to set
	 */
	public void setEvents(List<Event> set) {
		this.events =  set;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param vector the questions to set
	 */
	public void setQuestions(List<Question> vector) {
		this.questions = vector;
	}

	public void onDateSelect(SelectEvent e) {
		Date selectedDate = (Date) e.getObject();
		// Formatear la fecha a otro formato deseado (por ejemplo, "dd/MM/yyyy")
		SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String formattedDate = sdf.format(selectedDate);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + formattedDate));
		
		this.setEvents((List<Event>) bl.getEvents((Date) e.getObject()));
	}

	public void showQuestions() {
		this.setQuestions((List<Question>) bl.getQuestions(event));
	}
}
