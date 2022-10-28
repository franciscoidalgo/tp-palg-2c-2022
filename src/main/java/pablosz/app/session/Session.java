package pablosz.app.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SESSIONS")
public class Session {


    @Id
    @Column(name = "SESSION_KEY")
    private long key;

    @Column(name = "TIMEOUT")
    private int timeout;

    //@OneToMany
    //private List<PersistentObject> persistentObjects;

    public Session() {
    }

    public Session(long key, int timeout) {
        this.key = key;
        this.timeout = timeout;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
