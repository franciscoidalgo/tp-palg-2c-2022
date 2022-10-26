package pablosz.app.persistance.persisentObject;

public class PersistenceObjectBuilder {

    PersistedObject po;

    public PersistenceObjectBuilder() {
        super();
        this.po = new PersistedObject();
    }

    public PersistenceObjectBuilder setSessionKey(long key) {
        this.po.setSessionKey(key);
        return this;
    }

    public PersistenceObjectBuilder setClassName(String className) {
        this.po.setClassName(className);
        return this;
    }

    public PersistenceObjectBuilder setData(String data) {
        this.po.setData(data);
        return this;
    }

    public PersistedObject build() {
        return this.po;
    }
}
