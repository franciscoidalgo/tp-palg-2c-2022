package pablosz.app.persistance.persisentObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PersistentObjectQuery {

    public static Query deleteQuery(EntityManager em, long key, String className) {
        return em.createQuery("delete from PersistentObject where sessionKey=:sessionKey and className=:className").setParameter("sessionKey", key).setParameter("className", className);
    }

    public static Query selectQuery(EntityManager em, long key, String className) {
        return em.createQuery("from PersistentObject where sessionKey=:sessionKey and className=:className").setParameter("sessionKey", key).setParameter("className", className);
    }

}
