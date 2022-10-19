package pablosz.app.persistance;

public class InvalidPersistException extends Exception{
    public InvalidPersistException(){
        super("Tried to persist and Object with the NotPersistable annotation");
    }
}
