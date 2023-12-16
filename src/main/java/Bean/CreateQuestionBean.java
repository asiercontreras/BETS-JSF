package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import Bean.*;
import dominio.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import businessLogic.BLFacadeHibernateInterface;
import businessLogic.BLFacadeHibernate;
import resources.*;
import configuration.*;
import dataAccess.DataAccessHibernate;

@ManagedBean(name = "createQuestion")
@RequestScoped
public class CreateQuestionBean {

	// private Event evento;
	private Date fecha;
	private Event event;
	private Question question;
	private int minBet;
	private Vector<Event> events;
	private BLFacadeHibernateInterface bl;
	private String stringQuestion;

	public CreateQuestionBean() {
		bl = BLFacadeHibernate.getInstance();
	}

	public void createQuestion() throws EventFinished, QuestionAlreadyExist {
		question = bl.createQuestion(event, stringQuestion, minBet);
		System.out.println(question.toString());
	}

	public void listener(AjaxBehaviorEvent evento) {
		System.out.println(
				"Evento:" + event.getDescription() + " -> " + event.getEventDate() + " -> " + event.getEventDate());

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				"Evento:" + event.getDescription() + " -> " + event.getEventDate() + " -> " + event.getEventDate()));
	}

	public void getEvent(Date fecha) {
		this.events = bl.getEvents(fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
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

	public int getMinBet() {
		return minBet;
	}

	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}

	public String getstringQuestion() {
		return this.stringQuestion;
	}

	public void setstringQuestion(String stringQuestion) {
		this.stringQuestion = stringQuestion;
	}

	public void onDateSelect(SelectEvent e) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + e.getObject()));
		// System.out.println(bl.getEvents((Date) e.getObject()));
		System.out.println("-----------------------------------------------------");
		System.out.println("Los Eventos ANTES:" + this.getEvents());

		this.events = bl.getEvents((Date) e.getObject());

		System.out.println("-----------------------------------------------------");
		System.out.println("Los Eventos DESPUES:" + this.getEvents());

	}

	public Vector<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Vector<Event> eventos) {
		this.events = eventos;
		// System.out.println(eventos.toString());
	}

	/*
	 * public void onAsignarClave(SelectEvent e) { eventos =
	 * bf.getEvents((Date)e.getObject()); }
	 */

	/*
	 * public Vector<Event> getEventsBD(Date date){ eventos = bf.getEvents(fecha);
	 * return eventos; }
	 */
}
