package pablosz.app.persistance;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import pablosz.app.domain.PersistentObject;
import javax.persistence.EntityManager;

@Getter
public class CustomOrm {
    @Autowired
    private EntityManager em;

    public CustomOrm() {
    }

    public void persist(Object o) throws IllegalAccessException {
        PersistentObject po = new PersistentObject(o);
        em.persist(po);
    }
}
