package doopies.exception;

/**
 * Exception thrown when a task description is missing.
 * <p>
 * This exception is used to indicate that a required task description
 * is missing when creating a new task.
 * </p>
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs an {@code EmptyDescriptionException} with the specified error message.
     *
     * @param message The task type (e.g., "todo", "deadline", or "event") for which the description is missing.
     */
    public EmptyDescriptionException(String message) {
        super(String.format("OOPS!!! The description of a %s cannot be empty.",
                message));
    }
}
