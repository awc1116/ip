package doopies.exception;

/**
 * Exception thrown when an invalid task type is encountered.
 * <p>
 * This exception is used to indicate that the specified task type
 * is not recognized or supported by the application.
 * </p>
 */
public class InvalidTaskTypeException extends Exception {

    /**
     * Constructs an {@code InvalidTaskTypeException} with the specified error message.
     *
     * @param message A descriptive error message indicating the invalid task type.
     */
    public InvalidTaskTypeException(String message) {

        super(message);
    }
}
