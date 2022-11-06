package pablosz.app.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class SessionChecker {

    @Autowired
    EntityManager em;


    @Transactional
    public void closeSessionInBd(long key) {
        em.createQuery("update Session set isOpen=false where key=:key")
                .setParameter("key", key)
                .executeUpdate();
    }
}
