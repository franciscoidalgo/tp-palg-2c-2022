package pablosz.test;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import pablosz.app.Application;
import pablosz.app.persistance.CustomORM;
import pablosz.app.persistance.exceptions.FailedDeletionException;
import pablosz.app.persistance.exceptions.FailedSessionDeletion;
import pablosz.app.persistance.exceptions.InvalidPersistException;
import pablosz.app.persistance.persisentObject.PersistentObjectQuery;
import pablosz.test.objects.Persona;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Rollback(false)
@SpringBootTest(classes = Application.class)
public class PersistentObjectTest {

    @Autowired
    private EntityManager em;
    private CustomORM persistidor;

    private Persona examplePersona;
    private long key;

    @BeforeEach
    public void setupExampleObjects() {
        persistidor = new CustomORM();
        persistidor.setEm(em);
        examplePersona = new Persona("Some name", "Some last name", 25, 180);
        key = new Random().nextLong();
    }

    @Test
    public void storesAnObject() throws InvalidPersistException {
        persistidor.store(key, examplePersona);
        PersistentObjectQuery.selectQuery(em, key, Persona.class.getName())
                .getSingleResult();
    }

    @Test
    public void loadsAnObject() throws InvalidPersistException {
        persistidor.store(key, examplePersona);

        Persona loadedPersona = (Persona) persistidor.load(key, Persona.class);

        assertEquals(examplePersona.nombre, loadedPersona.nombre);
        assertEquals(examplePersona.apellido, loadedPersona.apellido);
        assertEquals(examplePersona.edad, loadedPersona.edad);
        assertEquals(examplePersona.getAuto().getMarca(), loadedPersona.getAuto().getMarca());
        assertEquals(examplePersona.getAuto().getModelo(), loadedPersona.getAuto().getModelo());

    }

    @Test
    public void removesAnObject() throws InvalidPersistException, FailedDeletionException {
        persistidor.store(key, examplePersona);
        persistidor.remove(key, Persona.class);
    }

    //TODO add tests for other methods
    @Test
    public void createsASession() {
        persistidor.createSession(key, 500000);
        List<Session> sessions = em.createQuery("from Session").getResultList();

        assertFalse(sessions.isEmpty());
    }

    @Test
    public void destroysASession() throws FailedSessionDeletion {
        persistidor.createSession(key, 5000);
        persistidor.destroySession(key);
    }

}
