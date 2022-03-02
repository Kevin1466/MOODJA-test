package exception;

/**
 * Throw when email has already been created by another manager.
 */
public class EmailExistsException extends Exception {
    public EmailExistsException(String operation, String email) {
        super(operation + " manager failed: email " + email + " exist.");
    }
}
