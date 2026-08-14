/**
 * Thrown when an operation references an account ID that does not exist
 * (either never created, or already closed).
 */
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
