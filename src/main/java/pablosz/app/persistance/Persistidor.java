package pablosz.app.persistance;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
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
				jsonElement.addProperty(field.getName(), gson.toJson(field.get(object)));
			}
		}
		
		PersistentObject persistentObject = new PersistentObject();
		persistentObject.setClassName(object.getClass().getName());
		persistentObject.setData(gson.toJson(jsonElement));
		persistentObject.setSessionKey(key);
		
		em.persist(persistentObject);
	}

	
	/*public <T> T toObject(Class<T> c) throws ClassNotFoundException {
		Gson gson = new Gson();
		return gson.fromJson(this.data, c);
	}*/
	
	
	@Transactional
	public void createSession(int key, int timeout) {
		Session session = new Session(key, timeout);
		em.persist(session);
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
