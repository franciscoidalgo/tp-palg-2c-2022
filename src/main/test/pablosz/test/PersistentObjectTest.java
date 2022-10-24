package pablosz.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import pablosz.app.Application;
import pablosz.app.persistance.CustomORM;
import pablosz.app.persistance.InvalidPersistException;
import pablosz.app.persistance.persisentObject.PersistentObjectQuery;
import pablosz.test.objects.Auto;
import pablosz.test.objects.Persona;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Rollback(false)
@SpringBootTest(classes = Application.class)
public class PersistentObjectTest {

    private final Persona persona = new Persona("nombrePersona", "apellidoPersona", 22, 180);
    private final Auto auto = new Auto("Ford", "Fiesta");
    @Autowired
    private EntityManager em;
    private CustomORM persistidor;

    private Persona examplePersona;
    private long key;

    @BeforeAll
    public void setup() {
        persistidor = new CustomORM();
        persistidor.setEm(em);
    }

    @BeforeEach
    public void setupExampleObjects() {
        examplePersona = new Persona("Some name", "Some last name", 25, 180);
        key = new Random().nextLong();
    }

    @Test
    public void storesAnObject() {
        List<Persona> storedPersonas = PersistentObjectQuery.selectQuery(em, key, Persona.class.getName()).getResultList();
        assertFalse(storedPersonas.isEmpty());
    }

    @Test
    public void loadsAnObject() {
        persistidor.store(key, examplePersona);

        Persona loadedPersona = (Persona) persistidor.load(key, Persona.class);

        assertEquals(examplePersona.nombre, loadedPersona.nombre);
        assertEquals(examplePersona.apellido, loadedPersona.apellido);
        assertEquals(examplePersona.edad, loadedPersona.edad);
        assertEquals(examplePersona.getAuto().getMarca(), loadedPersona.getAuto().getMarca());
        assertEquals(examplePersona.getAuto().getModelo(), loadedPersona.getAuto().getModelo());

    }

    //TODO add tests for other methods
    @Test
    public void createsASession() throws IllegalAccessException, InvalidPersistException {

    }

}
