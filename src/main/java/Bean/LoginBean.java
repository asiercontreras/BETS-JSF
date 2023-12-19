package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacadeHibernate;
import businessLogic.BLFacadeHibernateInterface;
import dominio.User;

public class LoginBean {

	private String usuario;
	private String password;
	private BLFacadeHibernateInterface bl;
	
	
	public LoginBean() {
		bl = BLFacadeHibernate.getInstance();
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String checkUserPass() {
		boolean isLogin = bl.checkUserPass(usuario, password);
		if(isLogin) {
			System.out.println("Iniciado sesion: " + usuario + " con contrasenna: " + password);
			/*FacesContext.getCurrentInstance().addMessage("mensajeLoginFinal",
					new FacesMessage("Sesion iniciada."));*/
			return "iniciado";
		}
		else {
			System.out.println("No existe este usuario en la BD o la contrasenna es erronea.");
			FacesContext.getCurrentInstance().addMessage("mensajeLoginFinal",
					new FacesMessage("No existe este usuario en la BD o la contrasenna es erronea."));
			return "noInciado";
		}
	}
	
	public void listener(AjaxBehaviorEvent evento) {
		System.out.println(
				"Login user:" + usuario + " -> " + password);

		FacesContext.getCurrentInstance().addMessage("mensajeLoginFinal", new FacesMessage(
				"Login user:" + usuario + " -> " + password));
	}
}