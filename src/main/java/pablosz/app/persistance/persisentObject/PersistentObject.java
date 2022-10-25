package pablosz.app.persistance.persisentObject;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "PERSISTENT_OBJECT")
public class PersistentObject {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "SESSION_KEY")
    private long sessionKey;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "DATA")
    private String data; // Json

    public PersistentObject() {
        super();
    }

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
    
    
}
