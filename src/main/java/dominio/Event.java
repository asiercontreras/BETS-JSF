package dominio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.persistence.*;

@Entity
public class Event {
	@Id
	@GeneratedValue
	private Integer eventNumber;
	private String description;
	private Date eventDate;

//Se ha tenido que cambiar el vector a set porque hibernate no puede mappear vectores si no set.
//Problema encontrado para comentar en el documento, creo que sera im
	@OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	// private Vector<Question> questions = new Vector<Question>();
	private Set<Question> questions = new HashSet<Question>();

	public Event() {
	}

	public Event(int num, String nombre, Date fecha) {
		this.eventNumber = num;
		this.description = nombre;
		this.eventDate = fecha;
	}

	public Question addQuestion(String question, float betMinimum) {
		Question q = new Question(question, betMinimum, this);
		questions.add(q);
		return q;
	}

	public boolean DoesQuestionExists(String question) {

		for (Question q : this.getQuestions()) {
			if (q.getQuestion().compareTo(question) == 0)
				return true;
		}
		return false;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return description;
	}

}
