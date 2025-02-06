package doopies.exception;


/**
 * Exception thrown when a task index provided by the user is out of bounds.
 * <p>
 * This exception is used to indicate that the specified task index
 * does not exist in the current task list.
 * </p>
 */
public class IndexOutOfBoundException extends Exception {

    /**
     * Constructs an {@code IndexOutOfBoundException} with the specified invalid index.
     *
     * @param message The invalid index provided by the user.
     */
    public IndexOutOfBoundException(String message) {

        super(message + " is not in your list.");
    }
}
