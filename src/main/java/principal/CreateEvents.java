package principal;

import modelo.HibernateUtil;
import dominio.Event;
import dominio.Question;

import org.hibernate.Session;
import java.util.*;

public class CreateEvents {
	public CreateEvents() {
	}

	public void createAndStoreEvent(int id, String descripcion, Date fecha, Vector<Question> q) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event e = new Event();
		e.setEventNumber(id);
		e.setDescription(descripcion);
		e.setEventDate(fecha);
		e.setQuestions(q);
		session.save(e);

		// OPERACIONES con la BD (que componen la transacción)
		session.getTransaction().commit();
	}

	private List listaEventos() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Event").list();
		session.getTransaction().commit();
		return result;
	}

	public static void main(String[] args) {

		Vector<Question> q = new Vector<Question>();
		CreateEvents e = new CreateEvents();
		System.out.println("Creación de eventos:");
		e.createAndStoreEvent(1, "Atleti-Real", new Date(), q);
		e.createAndStoreEvent(2, "Barsa-Madrid", new Date(), q);
		e.createAndStoreEvent(3, "Alaves-Osasuna", new Date(), q);
		System.out.println("Listado de eventos:");

		List eventos = e.listaEventos();
		for (int i = 0; i < eventos.size(); i++) {
			Event ev = (Event) eventos.get(i);
			System.out.println("Id: " + ev.getEventNumber() + " Descripcion: " + ev.getDescription() + " Fecha: "
					+ ev.getEventDate() + " Questions: " + ev.getQuestions());
		}
	}

//	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	 session.beginTransaction();
//	 // OPERACIONES con la BD (que componen la transacción)
//	 session.getTransaction().commit(); 

}
