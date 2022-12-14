package pablosz.app.persistance.persisentObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PersistentObjectQuery {

    public static Query deleteQuery(EntityManager em, long key, String className) {
        return em.createQuery("delete from PersistedObject where sessionKey=:sessionKey and className=:className")
                .setParameter("sessionKey", key)
                .setParameter("className", className);
    }

    public static Query selectQuery(EntityManager em, long key, String className) {
        return em.createQuery("from PersistedObject where sessionKey=:sessionKey and className=:className")
                .setParameter("sessionKey", key)
                .setParameter("className", className);
    }

    public static Query selectQuery(EntityManager em, long key) {
        return em.createQuery("from PersistedObject where sessionKey=:sessionKey")
                .setParameter("sessionKey", key);
    }

    public static Query updateQuery(EntityManager em, long key, PersistedObject po) {
        return em.createQuery("update PersistedObject set data=:poData where sessionKey=:sessionKey and className=:className")
                .setParameter("poData", po.getData())
                .setParameter("sessionKey", key)
                .setParameter("className", po.getClassName());
    }

}
