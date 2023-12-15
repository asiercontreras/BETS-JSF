package Bean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import domain.Event;
import domain.Question;

public class QueryQuestionBean {
	private Date date;
	private Event event;
	private Question question;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public void onDateSelect(SelectEvent e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + e.getObject()));
		System.out.println(bf.getEvents((Date) e.getObject()));
		setEventos(bf.getEvents((Date) e.getObject()));
	}
}
