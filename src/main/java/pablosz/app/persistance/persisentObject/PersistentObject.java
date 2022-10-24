package pablosz.app.persistance.persisentObject;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "PERSISTENT_OBJECT")
@Getter
@Setter
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

    public PersistentObject() {
        super();
    }
}
