package pablosz.app.persistance.persisentObject;

public class PersistenceObjectBuilder {

    PersistentObject po;

    public PersistenceObjectBuilder() {
        super();
        this.po = new PersistentObject();
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

    public PersistentObject build() {
        return this.po;
    }
}
