package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import domain.Event;
import domain.Question;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import Bean.*;
import resources.*;
import configuration.*;

public class CreateQuestionBean {
	private Date fecha;
	private Event partido;
	private Question pregunta;
	private int minBet;
	private Vector<Event> eventos;
	private BeanDataAccess bda;
	private BLFacade bf;
	private int claveEvento;
	
	public CreateQuestionBean() {
		//configxml = ConfigXML.getInstance();
		eventos = new Vector<Event>();
		//bda = new BeanDataAccess();
		bda = BeanDataAccess.getInstance();
		//eventos.add(new Event("Evento 1", new Date(2021, 12, 31)));
		//eventos.add(new Event("Evento 2", new Date(2020, 12, 31)));
		bf = bda.getBLFAcade();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Event getPartido() {
		return partido;
	}

	public void setPartido(Event partido) {
		this.partido = partido;
	}

	public Question getPregunta() {
		return pregunta;
	}

	public void setPregunta(Question pregunta) {
		this.pregunta = pregunta;
	}

	public int getMinBet() {
		return minBet;
	}

	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}
	
	public void onDateSelect(SelectEvent e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + e.getObject()));
		System.out.println(bf.getEvents((Date)e.getObject()));
		setEventos(bf.getEvents((Date)e.getObject()));
	}
	
	public Vector<Event> getEventos(){
		return this.eventos;
	}
	
	public void setEventos(Vector<Event> eventos) {
		this.eventos=eventos;
		//System.out.println(eventos.toString());
	}
	
	/*public void onAsignarClave(SelectEvent e) {
		eventos = bf.getEvents((Date)e.getObject());
	}*/
	
	/*public Vector<Event> getEventsBD(Date date){
		eventos = bf.getEvents(fecha);
		return eventos;
	}*/
}
