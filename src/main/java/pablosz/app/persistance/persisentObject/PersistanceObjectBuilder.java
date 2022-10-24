package pablosz.app.persistance.persisentObject;

public class PersistanceObjectBuilder {

    PersistentObject po;

    public PersistanceObjectBuilder() {
        super();
        this.po = new PersistentObject();
    }

    public PersistanceObjectBuilder setSessionKey(long key) {
        this.po.setSessionKey(key);
        return this;
    }

    public PersistanceObjectBuilder setClassName(String className) {
        this.po.setClassName(className);
        return this;
    }

    public PersistanceObjectBuilder setData(String data) {
        this.po.setData(data);
        return this;
    }

    public PersistentObject build() {
        return this.po;
    }
}
