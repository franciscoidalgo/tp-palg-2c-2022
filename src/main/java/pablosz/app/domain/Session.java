package pablosz.app.domain;

import lombok.Data;
import pablosz.app.persistance.PersistentObject;

import javax.persistence.*;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SESSIONS")
@Data
public class Session {

	
	@Id
	@Column(name = "SESSIONKEY")
	private long key;
	
	@Column(name = "TIMEOUT")
	private int timeout;

	//@OneToMany
	//private List<PersistentObject> persistentObjects;

	public Session() {}

	public Session(long key, int timeout) {
		this.key = key;
		this.timeout = timeout;
	}

	public long getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
