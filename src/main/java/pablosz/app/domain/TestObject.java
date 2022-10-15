package pablosz.app.domain;

import lombok.Data;
import pablosz.ann.NotPersistable;
import pablosz.ann.Persistable;

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
