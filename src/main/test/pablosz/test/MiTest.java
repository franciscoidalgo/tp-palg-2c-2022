package pablosz.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pablosz.app.Application;
import pablosz.app.persistance.InvalidPersistException;
import pablosz.app.persistance.PersistentObject;
import pablosz.app.domain.TestObject;
import pablosz.app.persistance.CustomOrm;

@SpringBootTest(classes=Application.class)
public class MiTest
{
	@Autowired
	private EntityManager em;

	private CustomOrm customOrm = new CustomOrm();

	@Test
	public void generatesPersistentObject() throws IllegalAccessException, ClassNotFoundException, InvalidPersistException {
		TestObject to = new TestObject("Some name", "More data", 25);
		PersistentObject po = new PersistentObject(to);
		System.out.println(po);
		TestObject recovered = po.toObject(TestObject.class);
		System.out.println(recovered);
	}
}
