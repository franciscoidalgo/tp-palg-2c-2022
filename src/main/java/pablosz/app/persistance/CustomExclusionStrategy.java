package pablosz.app.persistance;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

public class CustomExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return (fieldAttributes.getAnnotation(Persistable.class) == null && !fieldAttributes.getClass().isAnnotationPresent(Persistable.class)) || fieldAttributes.getAnnotation(NotPersistable.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return aClass.isAnnotationPresent(NotPersistable.class);
    }
}
