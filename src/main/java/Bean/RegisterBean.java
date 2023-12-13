package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegisterBean {

	/**
	 * Hay que acordarse que es una aplicación para apuestas. Necesitamos el dni,
	 * el nombre, apellidos y posiblemente algún método de pago.
	 */
    private String username;

    // Getter and Setter for the username property
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Method to handle form submission
    public String submit() {
        // Perform any necessary processing with the submitted data
        // For example, you might save the username to a database

        // After processing, navigate to a success page (could be the same page or a different one)
        return "success"; // JSF navigation rule will determine where to go next
    }
}