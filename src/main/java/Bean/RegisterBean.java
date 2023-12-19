package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import businessLogic.BLFacadeHibernate;
import businessLogic.BLFacadeHibernateInterface;

@ManagedBean
@RequestScoped
public class RegisterBean {
	
	

	/**
	 * Hay que acordarse que es una aplicaci�n para apuestas. Necesitamos el dni,
	 * el nombre, apellidos y posiblemente alg�n m�todo de pago.
	 */
	
	
	private BLFacadeHibernateInterface bl;
	
	
    private String username;
    
    //Deber�a llegarnos en Base64
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

    public void register() {
    	if (bl.insertUser(username, password)) {
    		System.out.println("Se ha insertado " + this.getUsername() + " correctamente." + " Su pass es: " + this.getPassword());
    		FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
    				new FacesMessage("Registrado."));
    		//return "registrado";
    	} else{
    		System.out.println("Incorrecto");
    		FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
    				new FacesMessage("El nombre de usuario con el que te quieres registrar ya existe."));
    		//return "noRegistrado";
    	}
    	//return "success";
    }
    
    public void listener(AjaxBehaviorEvent evento) {
		System.out.println(
				"Registe user:" + username + " -> " + password);

		FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal", new FacesMessage(
				"Reister user:" + username + " -> " + password));
	}
}