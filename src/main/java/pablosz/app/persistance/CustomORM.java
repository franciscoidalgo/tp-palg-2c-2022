package pablosz.app.persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pablosz.app.domain.Session;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.persisentObject.PersistedObject;
import pablosz.app.persistance.persisentObject.PersistenceObjectBuilder;
import pablosz.app.persistance.persisentObject.PersistentObject;
import pablosz.app.persistance.persisentObject.PersistentObjectQuery;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomORM implements PersistentObject {

    @Autowired
    private EntityManager em;

    public CustomORM() {
    }


    @Transactional()
    public void store(long key, Object object) {
        if (!object.getClass().isAnnotationPresent(NotPersistable.class)) {
            List<PersistedObject> storedObjects = (List<PersistedObject>) PersistentObjectQuery.selectQuery(em, key, object.getClass().getName()).getResultList();

            CustomExclusionStrategy customExclusionStrategy = new CustomExclusionStrategy();
            Gson gson = new GsonBuilder().addSerializationExclusionStrategy(customExclusionStrategy).create();

            PersistedObject persistedObject = new PersistenceObjectBuilder()
                    .setClassName(object.getClass().getName())
                    .setSessionKey(key)
                    .setData(gson.toJson(object))
                    .build();

            if (storedObjects != null && storedObjects.size() > 0) {
                PersistentObjectQuery.updateQuery(em, key, persistedObject).executeUpdate();
            } else {
                em.persist(persistedObject);
            }
        }
    }

    @Transactional
    public Object load(long key, Class<?> clazz) {

        try {
            String jsonObject = ((PersistedObject) PersistentObjectQuery.selectQuery(em, key, clazz.getName())
                    .getSingleResult())
                    .getData();
            System.out.println(jsonObject);
            Gson gson = new Gson();
            return gson.fromJson(jsonObject, clazz);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Object remove(long key, Class<?> clazz) {

        Object result = this.load(key, clazz);
        PersistentObjectQuery.deleteQuery(this.em, key, clazz.getName()).executeUpdate();

        return result;
    }

    @Transactional
    public void createSession(long key, int timeout) {
        Session session = new Session(key, timeout);
        em.persist(session);
    }

    @Transactional
    public void destroySession(long key) {
        em.createQuery("delete from Session where key=:sessionKey")
                .setParameter("sessionKey", key)
                .executeUpdate();
    }

    public EntityManager getEm() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
