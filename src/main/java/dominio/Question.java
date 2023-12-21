package dominio;

import java.util.HashSet;
import java.util.Set;

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
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Bet> bets = new HashSet<Bet>();

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
	
	public Set<Bet> getBets(){
		return this.bets;
	}
	
	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}

	public boolean doesBetExist(String description) {
		for(Bet b: bets) {
			if(b.getDescription().compareTo(description) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public Bet addBet(String description, float minBet) {
		Bet bet = new Bet(this, description, minBet);
		bets.add(bet);
		return bet;
	}
	

}
