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
	private User userLogin;
	
	
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
	
	public User getUserLogin() {
		return this.userLogin;
	}
	
	public void setUserLogin(User ul) {
		this.userLogin = ul;
	}
	
	public String checkUserPass() {
		boolean isLogin = bl.checkUserPass(usuario, password);
		if(isLogin) {
			System.out.println("Iniciado sesion: " + userLogin.toString());
			return "iniciado";
		}
		else {
			System.out.println("No existe este usuario en la BD o la contrasenna es erronea.");
			/*FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"No existe este usuario: " + this.usuario + " en la BD o la contrasenna "
							+ this.password + " es erronea."));*/
			return "noInciado";
		}
	}
}