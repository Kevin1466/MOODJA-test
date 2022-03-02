package exception;

/**
 * Throw when trying to delete a manager that has work left.
 */
public class HasUnhandledWorkException extends Exception {
    public HasUnhandledWorkException(Integer id) {
        super("Delete failed: Manager No. " + id + "has work that need to be finished.");
    }
}
