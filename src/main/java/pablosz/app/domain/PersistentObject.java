package pablosz.app.domain;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import pablosz.ann.Persistable;
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

	public PersistentObject(Object o) throws IllegalAccessException {
		Gson gson = new Gson();
		JsonObject jsonElement = new JsonObject();
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields){
			if (field.isAnnotationPresent(Persistable.class)){
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
