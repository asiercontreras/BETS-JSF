package dominio;

import javax.persistence.*;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Integer questionNumber;
	private String question;
	private float betMinimum;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Event event;

	public Question() {
	}

	public Question(String question, float betMinimum, Event event) {
		this.question = question;
		this.betMinimum = betMinimum;
		this.event = event;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
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

	public Event getEvent() {
		return event;
	}

	public void setEvento(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return question;
	}
	

}
