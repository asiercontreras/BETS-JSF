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
	 * Hay que acordarse que es una aplicación para apuestas. Necesitamos el dni,
	 * el nombre, apellidos y posiblemente algún método de pago.
	 */
	
	
	private BLFacadeHibernateInterface bl;
	
	
    private String username;
    
    //Debería llegarnos en Base64
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
    	if(campoVacio(username, password) && bl.insertUser(username, password)) {
    		System.out.println("CORRECTO: Se ha insertado " + this.getUsername() + " correctamente." + " Su pass es: " + this.getPassword());
    		FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
    				new FacesMessage("CORRECTO: Registrado correctamente el usuario " + username + "."));
    		//return "registrado";
    	}
    	else if (!campoVacio(username, password)) {
    		System.out.println("INCORRECTO: No se admiten usuarios ni contraseñas vacías.");
    		FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
    				new FacesMessage("INCORRECTO: No se admiten usuarios ni contraseñas vacías."));
    		//return "noRegistrado";
    	} else{
    		System.out.println("INCORRECTO: El nombre de usuario con el que te quieres registrar ya existe.");
    		FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
    				new FacesMessage("INCORRECTO: El nombre de usuario con el que te quieres registrar ya existe."));
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
    
    public boolean campoVacio(String user, String password) {
    	return user != "" && password != "";
    }
}