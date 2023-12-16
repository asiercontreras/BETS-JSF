package dominio;

import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event {
	@Id
	private Integer eventNumber;
	private String description;
	private Date eventDate;
	private Vector<Question> questions = new Vector<Question>();

	public Event(int num, String nombre, Date fecha) {
		this.eventNumber = num;
		this.description = nombre;
		this.eventDate = fecha;
	}
	
	public Question addQuestion(String question, float betMinimum)  {
	    Question q=new Question(question,betMinimum, this);
	    questions.add(q);
	    return q;
	    }

	public boolean DoesQuestionExists(String question)  {

	    for (Question q:this.getQuestions()){
	        if (q.getQuestion().compareTo(question)==0)
	            return true;}
	    return false;}

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

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

}
