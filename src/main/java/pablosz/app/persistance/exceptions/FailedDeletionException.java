package pablosz.app.persistance.exceptions;

public class FailedDeletionException extends Exception {
    public FailedDeletionException() {
        super("Error deleting an object: No changes applied.");
    }
}
