package doopies.notebook;

/**
 * Represents a to-do task in the {@code Doopies} application.
 * <p>
 * A to-do task is a simple task with:
 * <ul>
 *     <li>A description of the task.</li>
 *     <li>No specific date or time attached.</li>
 *     <li>A completion status (done or not done).</li>
 * </ul>
 * The task supports marking, unmarking, and a formatted string representation.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     *
     * @param task The description of the to-do task.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Constructs a new {@code ToDo} task with the specified description and completion status.
     *
     * @param task The description of the to-do task.
     * @param done The completion status of the to-do task.
     */
    public ToDo(String task, boolean done) {
        super(task, done);
    }

    /**
     * Marks this to-do task as completed.
     * <p>
     * This method creates and returns a new instance of the to-do task with its completion status set to done.
     * </p>
     *
     * @return A new {@code ToDo} instance marked as done.
     */
    @Override
    public ToDo mark() {
        return new ToDo(this.getTask(), true);
    }

    /**
     * Unmarks this to-do task (sets it as not done).
     * <p>
     * This method creates and returns a new instance of the to-do task with its completion status set to not done.
     * </p>
     *
     * @return A new {@code ToDo} instance marked as not done.
     */
    @Override
    public ToDo unmark() {
        return new ToDo(this.getTask(), false);
    }

    /**
     * Returns a string representation of the to-do task.
     * <p>
     * The string representation includes:
     * <ul>
     *     <li>The prefix "[T]" to indicate a to-do task.</li>
     *     <li>The task's completion status (marked as "X" if done, or " " if not done).</li>
     *     <li>The task description.</li>
     * </ul>
     * </p>
     *
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
