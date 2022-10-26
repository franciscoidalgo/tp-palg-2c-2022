package pablosz.test;

import org.junit.jupiter.api.AfterEach;
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
import pablosz.app.persistance.persisentObject.PersistentObject;
import pablosz.app.persistance.persisentObject.PersistentObjectQuery;
import pablosz.test.objects.Animal;
import pablosz.test.objects.Auto;
import pablosz.test.objects.Persona;

import javax.persistence.NoResultException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(false)
@SpringBootTest(classes = Application.class)
public class PersistentObjectTest {

    @Autowired
    private CustomORM persistidor;

    private Persona examplePersona;
    private Auto exampleAuto;

    private long sessionKey = 9999;

    @BeforeEach
    public void setupExampleObjects() {
        examplePersona = new Persona("Some name", "Some last name", 25, 180);
        exampleAuto = new Auto("Toyota","supra");
        persistidor.createSession(sessionKey, 500000);
    }

    @AfterEach
    public void clean() throws FailedSessionDeletion {

        // Los objetos persistidos se eliminan cuando se destruye la sesion
        persistidor.destroySession(sessionKey);

    }

    @Test
    public void storesAnObject() throws InvalidPersistException, FailedDeletionException {
        persistidor.store(sessionKey, examplePersona);
        PersistentObject objectStored = (PersistentObject) PersistentObjectQuery.selectQuery(persistidor.getEm(), sessionKey, Persona.class.getName())
                .getSingleResult();

        assertNotNull(objectStored.getData());
        assertNotNull(objectStored.getClassName());

    }

    @Test
    public void loadsAnObject() throws InvalidPersistException, FailedDeletionException {
        persistidor.store(sessionKey, examplePersona);

        Persona loadedPersona = (Persona) persistidor.load(sessionKey, Persona.class);

        assertEquals(examplePersona.getNombre(), loadedPersona.getNombre());
        assertEquals(examplePersona.getApellido(), loadedPersona.getApellido());
        assertEquals(examplePersona.getEdad(), loadedPersona.getEdad());
        assertEquals(examplePersona.getAuto().getMarca(), loadedPersona.getAuto().getMarca());
        assertEquals(examplePersona.getAuto().getModelo(), loadedPersona.getAuto().getModelo());

        assertEquals(0, loadedPersona.getAltura());
        assertNull(loadedPersona.getAuto().getNumeroDeSerie());

    }

    @Test
    public void removesAnObject() throws InvalidPersistException, FailedDeletionException {
        persistidor.store(sessionKey, examplePersona);

        persistidor.remove(sessionKey, Persona.class);

        assertThrows(NoResultException.class, () -> persistidor.load(sessionKey, Persona.class));

    }
    //Update del objeto
    @Test
    public void alwaysStoreLastObject() throws InvalidPersistException, FailedDeletionException {
        persistidor.store(sessionKey, examplePersona);

        Persona examplePersona2 = new Persona("Another name", "Some last name", 50, 180);
        persistidor.store(sessionKey, examplePersona2);

        Persona loadedPersona = (Persona) persistidor.load(sessionKey, Persona.class);

        assertEquals(examplePersona2.getNombre(), loadedPersona.getNombre());
        assertEquals(examplePersona2.getApellido(), loadedPersona.getApellido());
        assertEquals(examplePersona2.getEdad(), loadedPersona.getEdad());
        assertEquals(examplePersona2.getAuto().getMarca(), loadedPersona.getAuto().getMarca());
        assertEquals(examplePersona2.getAuto().getModelo(), loadedPersona.getAuto().getModelo());

        assertEquals(0, loadedPersona.getAltura());
        assertNull(loadedPersona.getAuto().getNumeroDeSerie());

    }
    @Test
    public void storeTwoObjectsOneSession() throws InvalidPersistException {
        persistidor.store(sessionKey,examplePersona);
        persistidor.store(sessionKey, exampleAuto);

        List<PersistentObject> objectStored = (List<PersistentObject>) PersistentObjectQuery.selectQuery(persistidor.getEm(), sessionKey)
            .getResultList();

        assertEquals(2, objectStored.size());
    }

    @Test
    public void delectObjectException() {
        assertThrows(NoResultException.class, () -> {
            persistidor.remove(1001, Persona.class);
        });
    }
    
    @Test
    public void storeObjectNotPersistableShouldThrowInvalidPersistException() {
    	Animal animal = new Animal("perro");
        assertThrows(InvalidPersistException.class, () -> {
            persistidor.store(sessionKey, animal);
        });
    }
}
