package dominio;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	
	@Id
	private String username;
	private String password;
	private String salt;
	
	public User() {
	}
	
	public User(String user, String pass, String salt) {
		this.username = user;
		this.password = pass;
		this.salt = salt;
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
}
