package Bean;

import java.util.Date;

import domain.Event;
import domain.Question;
import businessLogic.BLFacade;
public class CreateQuestionBean {
	private Date fecha;
	private Event partido;
	private Question pregunta;
	private int minBet;

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
}
