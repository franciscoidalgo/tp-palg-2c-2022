package pablosz.app.persistance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;
import pablosz.app.domain.Session;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "persitent_objects")
@Data
public class PersistentObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Transient
	private Session session;

	@Column
	private String className;

	@Column
	private String data; // Json

	public PersistentObject() {
	}

	public PersistentObject(int key, int timeout, String className, String data) {
		this.session = new Session(key, timeout);
		this.className = className;
		this.data = data;
	}

	public PersistentObject(Object o) throws IllegalAccessException, InvalidPersistException {
		Gson gson = new Gson();
		JsonObject jsonElement = new JsonObject();
		Class clazz = o.getClass();
		if (clazz.isAnnotationPresent(NotPersistable.class)) throw new InvalidPersistException();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields){
			if (field.isAnnotationPresent(Persistable.class) || (clazz.isAnnotationPresent(Persistable.class) && !field.isAnnotationPresent(NotPersistable.class))){
				jsonElement.addProperty(field.getName(), gson.toJson(field.get(o)));
			}
		}
		this.className = o.getClass().getName();
		this.data = gson.toJson(jsonElement);
	}

	public <T> T toObject(Class<T> c) throws ClassNotFoundException {
		Gson gson = new Gson();
		return gson.fromJson(this.data, c);
	}

}