package pablosz.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SESSIONS")
@Getter
@Setter
public class Session {


    @Id
    @Column(name = "SESSIONKEY")
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

}
