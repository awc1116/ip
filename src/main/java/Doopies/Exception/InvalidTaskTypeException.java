package doopies.exception;

/**
 * Exception thrown when an invalid task type is encountered.
 */
public class InvalidTaskTypeException extends Exception {
    /**
     * Constructs an InvalidTaskTypeException with the specified error message.
     *
     * @param message The error message indicating an invalid task type.
     */
    public InvalidTaskTypeException(String message) {

        super(message);
    }
}
