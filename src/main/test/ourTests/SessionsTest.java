package ourTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import pablosz.app.Application;
import pablosz.app.domain.Session;
import pablosz.app.persistance.CustomORM;
import pablosz.app.persistance.exceptions.FailedSessionDeletion;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Rollback(false)
@SpringBootTest(classes = Application.class)
public class SessionsTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CustomORM persistidor;

    private long sessionKey = 1;

    @Test
    public void createAndDestroySession() throws FailedSessionDeletion {
        persistidor.createSession(sessionKey, 500000);
        Session session = (Session) em.createQuery("from Session where key=:sessionKey")
                .setParameter("sessionKey", sessionKey)
                .getSingleResult();

        assertNotNull(session);

        persistidor.destroySession(sessionKey);

        List<Session> sessions = (List<Session>) em.createQuery("from Session where key=:sessionKey")
                .setParameter("sessionKey", sessionKey)
                .getResultList();

        assertEquals(0, sessions.size());
        
    }
}
