package doopies.exception;

/**
 * Exception thrown when a task index provided by the user is out of bounds.
 */
public class IndexOutOfBoundException extends Exception {
    /**
     * Constructs an IndexOutOfBoundException with the specified error message.
     *
     * @param message The error message indicating the index is out of bounds.
     */
    public IndexOutOfBoundException(String message) {

        super(message);
    }
}
