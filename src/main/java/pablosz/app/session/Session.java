package pablosz.app.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "IS_OPEN")
    private boolean isOpen;

    @Column(name = "OPENED_STAMP")
    private LocalDateTime openedTimeStamp;

    public Session() {
    }

    public Session(long key, long timeout) {
        this.key = key;
        this.timeout = timeout;
        this.openedTimeStamp = LocalDateTime.now();
        this.isOpen = true;
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

    public boolean isActive() {
        return ChronoUnit.MILLIS.between(openedTimeStamp, LocalDateTime.now()) < timeout;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
