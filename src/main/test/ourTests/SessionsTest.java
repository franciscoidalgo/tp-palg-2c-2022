package ourTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import pablosz.app.Application;
import pablosz.app.persistance.CustomORM;
import pablosz.app.session.Session;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Rollback(false)
@SpringBootTest(classes = Application.class)
public class SessionsTest {

    @Autowired
    private CustomORM persistidor;

    private long sessionKey = 1;

    @Test
    public void createAndDestroySession() {
        persistidor.createSession(sessionKey, 500000);
        Session session = (Session) persistidor.getEm().createQuery("from Session where key=:sessionKey")
                .setParameter("sessionKey", sessionKey)
                .getSingleResult();

        assertNotNull(session);

        persistidor.destroySession(sessionKey);

        List<Session> sessions = (List<Session>) persistidor.getEm().createQuery("from Session where key=:sessionKey")
                .setParameter("sessionKey", sessionKey)
                .getResultList();

        assertEquals(0, sessions.size());

    }
}
