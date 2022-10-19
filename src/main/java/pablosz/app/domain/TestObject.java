package pablosz.app.domain;

import lombok.Data;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

@Data
public class TestObject {
    @Persistable
    String name;

    @NotPersistable
    String otherData;

    @Persistable
    Integer number;

    public TestObject(String name, String otherData, Integer number) {
        this.name = name;
        this.otherData = otherData;
        this.number = number;
    }
}
