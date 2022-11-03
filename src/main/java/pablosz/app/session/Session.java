package pablosz.app.session;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "SESSIONS")
public class Session {


    @Id
    @Column(name = "SESSION_KEY")
    private long key;

    @Column(name = "TIMEOUT")
    private long timeout;

    @Transient
    private LocalDateTime openedTimeStamp;

    //@OneToMany
    //private List<PersistentObject> persistentObjects;

    public Session() {
    }

    public Session(long key, long timeout) {
        this.key = key;
        this.timeout = timeout;
        this.openedTimeStamp = LocalDateTime.now();
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isOpen() {
        return ChronoUnit.MILLIS.between(LocalDateTime.now(), openedTimeStamp) < timeout;
    }
}
