package doopies.exception;

/**
 * Exception thrown when an unknown command is entered by the user.
 * <p>
 * This exception is used to indicate that the user's input does not match any recognized command.
 * It provides a default error message informing the user that the command is not understood.
 * </p>
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs an {@code UnknownCommandException} with a default error message.
     */
    public UnknownCommandException() {

        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
