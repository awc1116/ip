package doopies.notebook;

/**
 * Represents a todo task in the doopies.userinterface.Doopies application.
 * <p>
 * A todo task is a simple task with no specific date or time attached.
 * It can be marked as done or not done and supports a string representation.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a new todo task with the specified description.
     *
     * @param task The description of the todo task.
     */
    public ToDo(String task) {

        super(task);
    }

    /**
     * Constructs a new todo task with the specified description and completion status.
     *
     * @param task The description of the todo task.
     * @param done The completion status of the todo task.
     */
    public ToDo(String task, boolean done) {

        super(task, done);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation creates and returns a new instance of the todo task
     *     with its completion status set to done.
     * </p>
     */
    @Override
    public ToDo mark() {

        return new ToDo(this.getTask(), true);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation creates and returns a new instance of the todo task
     *     with its completion status set to not done.
     * </p>
     */
    @Override
    public ToDo unmark() {

        return new ToDo(this.getTask(), false);
    }

    /**
     * Returns a string representation of the todo task.
     * <p>
     *     The string representation includes the prefix "[T]", followed by the task's
     *     completion status (marked as "X" if done, or " " if not done), and the task description.
     * </p>
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {

        return String.format("[T]%s", super.toString());
    }
}
