package pablosz.app.persistance;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Getter
public class CustomOrm {
    @Autowired
    private EntityManager em;

    public CustomOrm() {
    }

    public void persist(Object o) throws IllegalAccessException, InvalidPersistException {
        PersistentObject po = new PersistentObject(o);
        em.persist(po);
    }

    public <T> T find(Class<T> c, int id) throws ClassNotFoundException {
        PersistentObject po = em.find(PersistentObject.class, id);
        return po.toObject(c);
    }
}
