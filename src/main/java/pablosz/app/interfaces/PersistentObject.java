package pablosz.app.interfaces;

public interface PersistentObject {
    void store(long key, Object object);

    Object load(long key, Class<?> clazz);

    Object remove(long key, Class<?> clazz);

    void createSession(long key, int timeout);

    void destroySession(long key);

}
