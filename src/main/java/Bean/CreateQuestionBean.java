package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import dominio.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import businessLogic.BLFacadeHibernateInterface;
import businessLogic.BLFacadeHibernate;
//import resources.*;


@ManagedBean(name = "createQuestion")
@RequestScoped
public class CreateQuestionBean {

	// private Event evento;
	private Date fecha;
	private Event event;
	private Question question;
	private float minBet;
	private List<Event> events;
	private BLFacadeHibernateInterface bl;
	private String stringQuestion;

	public CreateQuestionBean() {
		bl = BLFacadeHibernate.getInstance();
	}

	public void createQuestion() throws EventFinished, QuestionAlreadyExist {
		System.out.println("String: " + stringQuestion);
		System.out.println("minBet: " + minBet);
		if (event == null) {
			FacesContext.getCurrentInstance().addMessage("mensajeFinal",
					new FacesMessage("No hay ningún evento seleccionado"));
			

		} else if (stringQuestion == "") {
			FacesContext.getCurrentInstance().addMessage("mensajeFinal",
					new FacesMessage("El evento no puede ser nulo"));

		} else {

			try {
				question = bl.createQuestion(event, stringQuestion, minBet);
				FacesContext.getCurrentInstance().addMessage("mensajeFinal",
						new FacesMessage("Evento con la pregunta: " + question.toString() + " creado"));
				stringQuestion="";
			} catch (QuestionAlreadyExist e) {
				FacesContext.getCurrentInstance().addMessage("mensajeFinal",
						new FacesMessage("Ya existe un evento con la pregunta " + question.toString() + "."));
			} catch (EventFinished event) {
				FacesContext.getCurrentInstance().addMessage("mensajeFinal",
						new FacesMessage("Estas añadiendo una pregunta a un evento pasado."));

			}
		}

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

	public float getMinBet() {
		return minBet;
	}

	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	public String getstringQuestion() {
		return this.stringQuestion;
	}

	public void setstringQuestion(String stringQuestion) {
		this.stringQuestion = stringQuestion;
	}

	public void onDateSelect(SelectEvent e) {
		Date selectedDate = (Date) e.getObject();
		// Formatear la fecha a otro formato deseado (por ejemplo, "dd/MM/yyyy")
		SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String formattedDate = sdf.format(selectedDate);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + formattedDate));
		// System.out.println(bl.getEvents((Date) e.getObject()));
		System.out.println("-----------------------------------------------------");
		System.out.println("Los Eventos ANTES:" + this.getEvents());

		this.events = bl.getEvents((Date) e.getObject());

		System.out.println("-----------------------------------------------------");
		System.out.println("Los Eventos DESPUES:" + this.getEvents());

	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> eventos) {
		this.events = eventos;
	}

	public void setEvento() {
		System.out.println("EVENTO----------------");

		System.out.println(event.toString());

	}

}
