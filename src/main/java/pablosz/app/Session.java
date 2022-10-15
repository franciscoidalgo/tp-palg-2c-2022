package pablosz.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {

	
	@Id
	@Column(name = "SESSIONKEY")
	private int key;
	
	@Column(name = "TIMEOUT")
	private int timeout;

	public Session() {}
	
	public Session(int key, int timeout) {
		super();
		this.key = key;
		this.timeout = timeout;
	}

	public int getKey() {
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
