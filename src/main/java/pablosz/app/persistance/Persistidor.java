package pablosz.app.persistance;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import pablosz.app.domain.Session;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

@Component
public class Persistidor {
	
	@Autowired
	private EntityManager em;

	public Persistidor() {
	}
	
	
	@Transactional
	public void store(long key, Object object) throws IllegalAccessException, InvalidPersistException {
	
		Gson gson = new Gson();
		JsonObject jsonElement = new JsonObject();
		Class clazz = object.getClass();
		if (clazz.isAnnotationPresent(NotPersistable.class)) throw new InvalidPersistException();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields){
			if (field.isAnnotationPresent(Persistable.class) || (clazz.isAnnotationPresent(Persistable.class) && !field.isAnnotationPresent(NotPersistable.class))){

				// string no pasar a json 
				
				jsonElement.addProperty(field.getName(), gson.toJson(field.get(object)));
				
			}
		}
		
		PersistentObject persistentObject = new PersistentObject();
		persistentObject.setClassName(object.getClass().getName());
		persistentObject.setData(gson.toJson(jsonElement));
		persistentObject.setSessionKey(key);
		
		em.persist(persistentObject);
	}
	
	public Object load(long key, Class<?> clazz) {
		Query query = em.createQuery("from PersistentObject where sessionKey=:sessionKey and className=:className")
				.setParameter("sessionKey", key)
				.setParameter("className", clazz.getName());
		
		String jsonObject = ((PersistentObject) query.getSingleResult()).getData();
		
		Gson gson = new Gson();
		return gson.fromJson(jsonObject, clazz);
	}

	
	/*public <T> T toObject(Class<T> c) throws ClassNotFoundException {
		Gson gson = new Gson();
		return gson.fromJson(this.data, c);
	}*/
	
	
	@Transactional
	public void createSession(long key, int timeout) {
		Session session = new Session(key, timeout);
		em.persist(session);
	}
	
	@Transactional
	public void destroySession(long key) {
		Session sessionToDestroy = em.find(Session.class, key);
		if(sessionToDestroy != null) {
			Query query = em.createQuery("delete from PersistentObject where sessionKey=:sessionKey")
					.setParameter("sessionKey", key);
			query.executeUpdate();
			em.remove(sessionToDestroy);
		}
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
