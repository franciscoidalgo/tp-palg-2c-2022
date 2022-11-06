package pablosz.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pablosz.app.Application;
import pablosz.app.interfaces.PersistentObject;
import pablosz.app.interfaces.SessionListener;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class)
public class SessionListenerTest implements SessionListener {
    private static final long loopThread = 1000;

    private static final long key1 = 1;
    private static final long timeOut1 = 5000;

    private static final long key2 = 2;
    private static final long timeOut2 = 10000;

    private int session1Opened = 0;
    private int session2Opened = 0;
    private int session1StillOpened = 0;
    private int session2StillOpened = 0;

    @Autowired
    private PersistentObject po;

    @BeforeEach
    public void init() {
        po.addListener(this);
        po.createSession(key1, timeOut1);
        po.createSession(key2, timeOut2);
    }

    @AfterEach
    public void destroy() {
        po.destroySession(key1);
        po.destroySession(key2);
    }

    @Test
    public void testSessionOpenedClosed() {
        assertTrue(session1Opened == 1);
        assertTrue(session2Opened == 1);

        esperar(timeOut1 / 2);
        assertTrue(session1Opened == 1);
        assertTrue(session2Opened == 1);

        esperar((timeOut1 / 2) + loopThread * 2);
        assertTrue(session1Opened == 0);
        assertTrue(session2Opened == 1);

        long restaEsperar = Math.abs(timeOut1 - timeOut2) + loopThread * 2;
        esperar(restaEsperar);
        assertTrue(session1Opened == 0);
        assertTrue(session2Opened == 0);
    }

    @Test
    public void testSessionStillOpenedClosed() {
        assertTrue(session2Opened == 1);

        // no deberia haberse llamado a sessionSTillOpen
        int i = 0;
        assertTrue(session2StillOpened == i);

        long acum = 0;
        while (acum < timeOut2) {
            // espero, aun debe estar abierta
            esperar(loopThread * 100);

            i++;
            assertTrue(session2StillOpened == i);

            acum += loopThread + 100;
        }

        // expiro el tiempo, paso a closed
        assertTrue(session2Opened == 0);

        // espero un loop, debe seguir en closed
        esperar(loopThread * 100);
        assertTrue(session2StillOpened == i - 1);
    }

    @Override
    public void sessionOpened(long key) {
        final int k = (int) key;
        final int k1 = (int) key1;
        final int k2 = (int) key2;

        switch (k) {
            case k1:
                session1Opened++;
                break;
            case k2:
                session2Opened++;
                break;
        }
    }

    @Override
    public void sessionStillOpened(long key) {
        final int k = (int) key;
        final int k1 = (int) key1;
        final int k2 = (int) key2;

        switch (k) {
            case k1:
                session1StillOpened++;
                break;
            case k2:
                session2StillOpened++;
                break;
        }
    }


    @Override
    public void sessionClosed(long key) {
        final int k = (int) key;
        final int k1 = (int) key1;
        final int k2 = (int) key2;

        switch (k) {
            case k1:
                session1Opened--;
                break;
            case k2:
                session2Opened--;
                break;
        }
    }

    @Override
    public void sessionStillClosed(long key) {
        final int k = (int) key;
        final int k1 = (int) key1;
        final int k2 = (int) key2;

        switch (k) {
            case k1:
                session1StillOpened++;
                break;
            case k2:
                session2StillOpened++;
                break;
        }
    }

    public void esperar(long n) {
        try {
            Thread.sleep(n);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
