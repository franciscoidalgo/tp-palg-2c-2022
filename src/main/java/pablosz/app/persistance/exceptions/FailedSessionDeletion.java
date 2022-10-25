package pablosz.app.persistance.exceptions;

public class FailedSessionDeletion extends Exception {
    public FailedSessionDeletion() {
        super("Error deleting session.");
    }
}
