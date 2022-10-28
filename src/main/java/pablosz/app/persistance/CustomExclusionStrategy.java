package pablosz.app.persistance;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

public class CustomExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        boolean attributeIsPersistable = fieldAttributes.getAnnotation(Persistable.class) != null;
        boolean classIsPersistable = fieldAttributes.getDeclaringClass().isAnnotationPresent(Persistable.class);
        boolean attributeIsNotPersistable = fieldAttributes.getAnnotation(NotPersistable.class) != null;
        return (!attributeIsPersistable && !classIsPersistable) || attributeIsNotPersistable;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return aClass.isAnnotationPresent(NotPersistable.class);
    }
}
