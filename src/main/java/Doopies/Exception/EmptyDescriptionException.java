package doopies.exception;

/**
 * Exception thrown when a task description is missing.
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Constructs an EmptyDescriptionException with the specified error message.
     *
     * @param message The error message indicating the missing description.
     */
    public EmptyDescriptionException(String message) {

        super(message);
    }
}
