package dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Bet {
	@Id
	@GeneratedValue
	private Integer foreNum;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Question question;
	private String description;
	private float moneyBet;
	
	public Bet() {
		
	}
	
	public Bet(Question question, String description, float moneyBet) {
		this.question = question;
		this.description = description;
		this.moneyBet = moneyBet;
	}
	
	public Integer getForeNum() {
		return this.foreNum;
	}
	
	public void setForeNum(Integer foreNum) {
		this.foreNum = foreNum;
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
		return this.moneyBet;
	}
	
	public void setMinBet(float moneyBet) {
		this.moneyBet = moneyBet;
	}
	
	@Override
	public String toString() {
		return this.description + " -> " + this.question + " -> " + this.moneyBet;
	}
}
