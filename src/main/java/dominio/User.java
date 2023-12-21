package dominio;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	
	@Id
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private String salt;
	private Date fechanac;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Bet> bets;
	
	
	
	/**
	 * @return the bets
	 */
	public Set<Bet> getBets() {
		return bets;
	}

	/**
	 * @param bets the bets to set
	 */
	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}

	public User() {
	}
	
	public User(String user, String pass) {
		this.username = user;
		this.password = pass;
		
	}
	
	public User(String user, String pass, String salt) {
		this.username = user;
		this.password = pass;
		this.salt = salt;
	}
	

	public User(String username, String password,String salt, String nombre, String apellido,  Date fechanac) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.salt = salt;
		this.fechanac = fechanac;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void addBet(Bet b) {
		bets.add(b);
	}
}
