package Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacadeHibernate;
import businessLogic.BLFacadeHibernateInterface;
import dominio.*;
import exceptions.BetAlreadyExist;

public class BetBean {
	private String description;
	private float minBet;
	private Question question;
	private List<Question> questions;
	private Event event;
	private List<Event> events;
	private Date fecha;
	private BLFacadeHibernateInterface bl;
	private Bet bet;
	private List<String> opciones;
	private String opcionElegida;

	public BetBean() {
		bl = BLFacadeHibernate.getInstance();
	}

	public void getQuestion(Event event) {
		this.questions = bl.getQuestions(event);
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getMinBet() {
		return this.minBet;
	}

	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	public List<Event> getEvent(Date fecha) {
		this.events = bl.getEvents(fecha);
		return this.events;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Bet getBet() {
		return this.bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void createBet() throws BetAlreadyExist {
		if (question == null) {
			FacesContext.getCurrentInstance().addMessage("mensajeFinalBet",
					new FacesMessage("No hay ninguna pregunta seleccionada"));
		} else if (this.description == "") {
			FacesContext.getCurrentInstance().addMessage("mensajeFinalBet",
					new FacesMessage("No puede haber una descripción vacía"));
		} else if (minBet < 0) {
			FacesContext.getCurrentInstance().addMessage("mensajeFinalBet",
					new FacesMessage("No puede haber apuesta mínima negativa"));
		} else if (minBet < question.getBetMinimum()) {
			FacesContext.getCurrentInstance().addMessage("mensajeFinalBet",
					new FacesMessage("No puede haber apuesta mínima negativa"));
		} else {
			try {
				bet = bl.createBet(question, opcionElegida, minBet);
				FacesContext.getCurrentInstance().addMessage("mensajeFinalBet",
						new FacesMessage("Apuesta añadida: " + bet.toString() + "."));
				this.description = "";
				this.minBet = 0;
			} catch (BetAlreadyExist bae) {
				FacesContext.getCurrentInstance().addMessage("mensajeFinalBet",
						new FacesMessage("Ya existe la apuesta con la apuesta " + bet.toString() + "."));
			}
		}
	}

	public void listener(AjaxBehaviorEvent evento) {
		System.out
				.println("Apuesta:" + event.getDescription() + " -> " + question.getQuestion() + " -> " + description);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				"Apuesta:" + event.getDescription() + " -> " + question.getQuestion() + " -> " + description));
	}

	public void onDateSelect(SelectEvent e) {
		Date selectedDate = (Date) e.getObject();
		// Formatear la fecha a otro formato deseado (por ejemplo, "dd/MM/yyyy")
		SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String formattedDate = sdf.format(selectedDate);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + formattedDate));
		/*
		 * // System.out.println(bl.getEvents((Date) e.getObject()));
		 * System.out.println("-----------------------------------------------------");
		 * System.out.println("Los Eventos ANTES:" + this.getEvent());
		 */

		this.events = bl.getEvents((Date) e.getObject());

		/*
		 * System.out.println("-----------------------------------------------------");
		 * System.out.println("Los Eventos DESPUES:" + this.getEvents());
		 */

	}

	public void setEvento() {
		System.out.println("EVENTO----------------");

		this.questions = bl.getQuestions(this.event);

		System.out.println(event.toString());

	}

	public void setPregunta() {
		System.out.println("PREGUNTA----------------");

		String texto = event.getDescription();
		
		System.out.println(texto);
		
        String[] resultado = texto.split("-");

        List <String> listaOpciones = new ArrayList<String>();
        for (String palabra : resultado) {
            if (!palabra.isEmpty()) {
            	listaOpciones.add(palabra);
            }
        }
        
        this.setOpciones(listaOpciones);
		
		System.out.println(opciones);

		System.out.println(question.toString());

	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public String getOpcionElegida() {
		return opcionElegida;
	}

	public void setOpcionElegida(String opcionElegida) {
		this.opcionElegida = opcionElegida;
	}
	
}