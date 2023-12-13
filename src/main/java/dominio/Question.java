package dominio;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	private Integer questionNumber;
	private Event evento;
	private String question;
	private float betMinimum;
	
	public Question() {}
	public Integer getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	public Event getEvento() {
		return evento;
	}
	public void setEvento(Event evento) {
		this.evento = evento;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public float getBetMinimum() {
		return betMinimum;
	}
	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

}
