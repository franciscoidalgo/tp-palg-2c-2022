package pablosz.app.persistance.exceptions;

public class InvalidPersistException extends Exception {
    public InvalidPersistException() {
        super("Error persisting an object: Tried to persist an Object with the NotPersistable annotation");
    }
}
