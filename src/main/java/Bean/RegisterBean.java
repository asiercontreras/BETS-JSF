package Bean;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

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
	 * Hay que acordarse que es una aplicación para apuestas. Necesitamos el dni, el
	 * nombre, apellidos y posiblemente algún método de pago.
	 */

	private BLFacadeHibernateInterface bl;

	private String username;

	// Debería llegarnos en Base64
	private String password;
	private String nombre;
	private String apellido;
	private Date fechanac;
	private Date maxYear;

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

		if (!campoVacio(username, password, nombre, apellido) || this.fechavacio(fechanac)) {
			System.out.println("INCORRECTO: No se admiten campos vacios.");
			FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
					new FacesMessage("INCORRECTO: No se admiten campos vacios."));
			// return "noRegistrado";
		} else if (!this.esMayordeEdad(fechanac)) {
			System.out.println("INCORRECTO: El usuario es menor de edad.");
			FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
					new FacesMessage("INCORRECTO: El usuario no cumple la mayoria de edad."));

		} else if (campoVacio(username, password, nombre, apellido) && bl.insertUser(username, password,nombre, apellido, fechanac)
				&& this.esMayordeEdad(fechanac)) {
			System.out.println("CORRECTO: Se ha insertado " + this.getUsername() + " correctamente." + " Su pass es: "
					+ this.getPassword());
			FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
					new FacesMessage("CORRECTO: Registrado correctamente el usuario " + username + "."));
			// return "registrado";
		} else {
			System.out.println("INCORRECTO: El nombre de usuario con el que te quieres registrar ya existe.");
			FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
					new FacesMessage("INCORRECTO: El nombre de usuario con el que te quieres registrar ya existe."));
			// return "noRegistrado";
		}
	}

	// return "success";

	public void listener(AjaxBehaviorEvent evento) {
		System.out.println("Registe user:" + username + " -> " + password);

		FacesContext.getCurrentInstance().addMessage("mensajeRegisterFinal",
				new FacesMessage("Reister user:" + username + " -> " + password));
	}

	public boolean campoVacio(String user, String password, String nombre, String apellido) {
		return user != "" && password != "" && nombre != "" && apellido != "";
	}

	public boolean fechavacio(Date d) {
		return d == null;
	}

	public boolean esMayordeEdad(Date d) {
		LocalDate localDateNacimiento = d.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(localDateNacimiento, fechaActual);
		int edadMinimaMayorEdad = 18;
		if (periodo.getYears() >= edadMinimaMayorEdad) {
			return true;
		} else {
			return false;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public Date getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(Date maxYear) {
		this.maxYear = maxYear;
	}
}