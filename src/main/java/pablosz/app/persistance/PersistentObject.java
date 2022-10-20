package pablosz.app.persistance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;
import pablosz.app.domain.Session;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Entity
@Table(name = "PERSISTENT_OBJECT")
@Data
public class PersistentObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "SESSION_KEY")
	private long sessionKey;

	@Column(name = "CLASS_NAME")
	private String className;

	@Column(name = "DATA")
	private String data; // Json

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(long sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public PersistentObject(int id, long sessionKey, String className, String data) {
		super();
		this.id = id;
		this.sessionKey = sessionKey;
		this.className = className;
		this.data = data;
	}
	
	public PersistentObject() {
		
	}
}
