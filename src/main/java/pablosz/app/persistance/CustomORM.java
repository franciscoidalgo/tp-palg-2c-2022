package pablosz.app.persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pablosz.app.domain.Session;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.exceptions.FailedDeletionException;
import pablosz.app.persistance.exceptions.FailedSessionDeletion;
import pablosz.app.persistance.exceptions.InvalidPersistException;
import pablosz.app.persistance.persisentObject.PersistanceObjectBuilder;
import pablosz.app.persistance.persisentObject.PersistentObject;
import pablosz.app.persistance.persisentObject.PersistentObjectQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
public class CustomORM {

    @Autowired
    private EntityManager em;

    public CustomORM() {
    }


    @Transactional
    public void store(long key, Object object) throws InvalidPersistException {
        if (object.getClass().isAnnotationPresent(NotPersistable.class)) throw new InvalidPersistException();
        CustomExclusionStrategy customExclusionStrategy = new CustomExclusionStrategy();
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(customExclusionStrategy).create();

        PersistentObject persistentObject = new PersistanceObjectBuilder()
                .setClassName(object.getClass().getName())
                .setSessionKey(key)
                .setData(gson.toJson(object))
                .build();

        em.persist(persistentObject);
    }

    @Transactional
    public Object load(long key, Class<?> clazz) {

        String jsonObject = ((PersistentObject) PersistentObjectQuery.selectQuery(em, key, clazz.getName())
                .getSingleResult())
                .getData();

        Gson gson = new Gson();
        return gson.fromJson(jsonObject, clazz);
    }

    @Transactional
    public Object remove(long key, Class<?> clazz) throws FailedDeletionException {

        Object result = this.load(key, clazz);
        if (PersistentObjectQuery.deleteQuery(this.em, key, clazz.getName()).executeUpdate() == 0)
            throw new FailedDeletionException();

        return result;
    }

    @Transactional
    public void createSession(long key, int timeout) {
        Session session = new Session(key, timeout);
        em.persist(session);
    }

    @Transactional
    public void destroySession(long key) throws FailedSessionDeletion {
        Query query = em.createQuery("delete from PersistentObject where sessionKey=:sessionKey")
                .setParameter("sessionKey", key);
        if (query.executeUpdate() != 1) throw new FailedSessionDeletion();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
