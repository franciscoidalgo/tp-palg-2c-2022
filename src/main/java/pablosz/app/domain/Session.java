package pablosz.app.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
@Data
public class Session {

	
	@Id
	@Column(name = "SESSIONKEY")
	private long key;
	
	@Column(name = "TIMEOUT")
	private int timeout;

	@Transient
	private List<PersistentObject> persistentObjects;

	public Session() {}

	public Session(long key, int timeout) {
		this.key = key;
		this.timeout = timeout;
	}
}
