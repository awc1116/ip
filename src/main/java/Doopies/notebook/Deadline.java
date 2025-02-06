package doopies.notebook;

import java.time.LocalDateTime;
import java.util.Optional;

import doopies.util.DateFormat;
import doopies.util.Parser;

/**
 * Represents a deadline task in the {@code Doopies} application.
 * <p>
 * A deadline task contains:
 * <ul>
 *     <li>A description of the task.</li>
 *     <li>A due date, stored as a {@code String} and optionally as a {@link LocalDateTime}.</li>
 *     <li>A completion status (marked as done or not done).</li>
 * </ul>
 * The due date can be formatted for display and parsed for date-based operations.
 * </p>
 */
public class Deadline extends Task {
    private final String deadline;
    private final Optional<LocalDateTime> dueDate;

    /**
     * Constructs a new {@code Deadline} task with the specified description and due date.
     *
     * @param task     The description of the deadline task.
     * @param deadline The due date of the task in string format.
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
        this.dueDate = Parser.parseMyDate(deadline);
    }

    /**
     * Constructs a new {@code Deadline} task with the specified description, completion status, and due date.
     *
     * @param task     The description of the deadline task.
     * @param done     The completion status of the task.
     * @param deadline The due date of the task in string format.
     */
    public Deadline(String task, boolean done, String deadline) {
        super(task, done);
        this.deadline = deadline;
        this.dueDate = Parser.parseMyDate(deadline);
    }

    /**
     * Retrieves the formatted due date as a string.
     * <p>
     * If the date is successfully parsed, it is returned in a formatted string.
     * Otherwise, the original raw string provided by the user is returned.
     * </p>
     *
     * @return The formatted due date, or the raw string if parsing fails.
     */
    public String getDeadline() {
        return this.dueDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.deadline);
    }

    /**
     * Retrieves the due date of the task as a {@link LocalDateTime}.
     * <p>
     * If the date cannot be parsed, this method returns {@link LocalDateTime#MAX}.
     * </p>
     *
     * @return The parsed due date as a {@link LocalDateTime}, or {@link LocalDateTime#MAX} if parsing fails.
     */
    public LocalDateTime getDeadlineDateTime() {
        return this.dueDate.orElse(LocalDateTime.MAX);
    }

    /**
     * Marks this deadline task as completed.
     * <p>
     * This method creates and returns a new instance of the deadline task with its completion status set to done.
     * </p>
     *
     * @return A new {@code Deadline} instance marked as done.
     */
    @Override
    public Deadline mark() {
        return new Deadline(this.getTask(), true, this.deadline);
    }

    /**
     * Unmarks this deadline task (sets it as not done).
     * <p>
     * This method creates and returns a new instance of the deadline task with its completion status set to not done.
     * </p>
     *
     * @return A new {@code Deadline} instance marked as not done.
     */
    @Override
    public Deadline unmark() {
        return new Deadline(this.getTask(), false, this.deadline);
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * The string representation includes:
     * <ul>
     *     <li>The prefix "[D]" to indicate a deadline task.</li>
     *     <li>The task's completion status.</li>
     *     <li>The task description.</li>
     *     <li>The formatted due date.</li>
     * </ul>
     * </p>
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDeadline());
    }
}
