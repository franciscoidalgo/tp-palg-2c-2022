package pablosz.app.interfaces;

public interface SessionListener {
    void sessionClosed(long key);

    void sessionStillClosed(long key);

    void sessionOpened(long key);

    void sessionStillOpened(long key);
}
