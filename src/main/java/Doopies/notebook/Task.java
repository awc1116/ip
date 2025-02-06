package doopies.notebook;

/**
 * Represents an abstract task in the {@code Doopies} application.
 * <p>
 * A task consists of:
 * <ul>
 *     <li>A description of the task.</li>
 *     <li>A completion status (done or not done).</li>
 * </ul>
 * Subclasses of {@code Task} must implement methods for marking and unmarking tasks.
 * </p>
 */
public abstract class Task {
    private final String task;
    private final boolean done;

    /**
     * Constructs a new {@code Task} with the specified description and an initial status of not done.
     *
     * @param task The description of the task.
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
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
     * @return {@code true} if the task is marked as done, otherwise {@code false}.
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
     * Marks the task as completed.
     * <p>
     * Subclasses must provide an implementation that creates and returns a new instance
     * of the task with its completion status set to done.
     * </p>
     *
     * @return A new {@code Task} instance marked as done.
     */
    abstract Task mark();

    /**
     * Unmarks the task, setting its status to not done.
     * <p>
     * Subclasses must provide an implementation that creates and returns a new instance
     * of the task with its completion status set to not done.
     * </p>
     *
     * @return A new {@code Task} instance marked as not done.
     */
    abstract Task unmark();

    /**
     * Returns a string representation of the task.
     * <p>
     * The string representation includes:
     * <ul>
     *     <li>The completion status, represented as "[X]" if done, or "[ ]" if not done.</li>
     *     <li>The task description.</li>
     * </ul>
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
