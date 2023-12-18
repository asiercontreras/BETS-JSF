package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import businessLogic.BLFacadeHibernate;
import businessLogic.BLFacadeHibernateInterface;

@ManagedBean
@RequestScoped
public class RegisterBean {
	
	

	/**
	 * Hay que acordarse que es una aplicación para apuestas. Necesitamos el dni,
	 * el nombre, apellidos y posiblemente algún método de pago.
	 */
	
	
	private BLFacadeHibernateInterface bl;
	
	
    private String username;
    private String password;
    
    
    public RegisterBean() {
    	bl = BLFacadeHibernate.getInstance();
    }

    // Getter and Setter for the username property
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

    public String register() {
    	if (bl.insertUser(username, password)) {
    		System.out.println("Se ha insertado " + this.getUsername() + " correctamente." + " Su pass es: " + this.getPassword());
    	} else System.out.println("Incorrecto");
    	
    	
    	return "success";
    }
}