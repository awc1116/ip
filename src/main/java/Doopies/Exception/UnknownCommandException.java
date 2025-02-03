package doopies.exception;

/**
 * Exception thrown when an unknown command is entered by the user.
 */
public class UnknownCommandException extends Exception {
    /**
     * Constructs an UnknownCommandException with the specified error message.
     *
     * @param message The error message describing the unknown command.
     */
    public UnknownCommandException(String message) {

        super(message);
    }
}
