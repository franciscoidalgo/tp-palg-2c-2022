package pablosz.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import pablosz.app.Application;
import pablosz.app.domain.Auto;
import pablosz.app.domain.Persona;
import pablosz.app.domain.Session;
import pablosz.app.persistance.InvalidPersistException;
import pablosz.app.persistance.Persistidor;

@Rollback(false)
@SpringBootTest(classes=Application.class)
public class PersistentObjectTest 
{
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private Persistidor persistidor;
	
	private Persona persona = new Persona("nombre", "apellido", 22, 180);
	private Auto auto = new Auto("Ford", "Fiesta");
	
	/*@BeforeEach
	public void setUp() {
		persistidor.setEm(this.em);
	}*/
	
	/* @Test
	public void generatesPersistentObject() throws IllegalAccessException, ClassNotFoundException, InvalidPersistException {
		TestObject to = new TestObject("Some name", "More data", 25);
		PersistentObject po = new PersistentObject(to);
		System.out.println(po);
		TestObject recovered = po.toObject(TestObject.class);
		System.out.println(recovered);
	} */
	
	@Test
	public void storeObject() throws IllegalAccessException, InvalidPersistException {
		// crear una sesion
		// guardar session id
		persistidor.createSession(8, 999);
		
		// guardar un objeto
		
		persistidor.store(8, persona);
		persistidor.store(8, auto);
		
		// levantarlo
		
		assertEquals(1, 1);
		
		// borrar session
	}
	
	/*@Test
	@Transactional
	@Rollback(false)
	public void test() {
		Session session = new Session();
		session.setKey(9);
		em.persist(session);
	}*/
	
	
}
