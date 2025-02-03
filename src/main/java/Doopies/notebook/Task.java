package doopies.notebook;

/**
 * Represents an abstract task in the doopies.Doopies application.
 * <p>
 * A task contains a description and a completion status. Subclasses of {@code Task} must provide
 * implementations for marking and unmarking the task.
 * </p>
 */
public abstract class Task {
    private final String task;
    private final boolean done;

    /**
     * Constructs a new task with the specified description and an initial status of not done.
     *
     * @param task The description of the task.
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Constructs a new task with the specified description and completion status.
     *
     * @param task The description of the task.
     * @param done The completion status of the task.
     */
    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Checks whether the task is marked as done.
     *
     * @return {@code true} if the task is marked as done; {@code false} otherwise.
     */
    public boolean isDone() {

        return this.done;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getTask() {

        return this.task;
    }

    /**
     * Marks the task as done.
     * <p>
     *     Subclasses must provide an implementation that creates and returns a new instance
     *     of the task with its completion status set to done.
     * </p>
     *
     * @return A new instance of the task marked as done.
     */
    abstract Task mark();

    /**
     * Unmarks the task as not done.
     * <p>
     *     Subclasses must provide an implementation that creates and returns a new instance
     *     of the task with its completion status set to not done.
     * </p>
     *
     * @return A new instance of the task marked as not done.
     */
    abstract Task unmark();

    /**
     * Returns a string representation of the task.
     * <p>
     *     The string representation includes the completion status (marked with an "X" if done)
     *     and the task description.
     * </p>
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.done ? "X" : " ",
                this.task);
    }
}
