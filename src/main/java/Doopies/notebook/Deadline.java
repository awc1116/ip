package doopies.notebook;

import java.time.LocalDateTime;
import java.util.Optional;

import doopies.util.DateFormat;
import doopies.util.Parser;

/**
 * Represents a deadline task in the doopies.Doopies application.
 * <p>
 *     A deadline task contains a description and a due date. It can be marked as done or not done and supports
 *     a string representation that includes its due date.
 * </p>
 */
public class Deadline extends Task {
    private final String deadline;
    private final Optional<LocalDateTime> dueDate;

    /**
     * Constructs a new deadline task with the specified description and deadline.
     *
     * @param task     The description of the deadline task.
     * @param deadline The due date of the task, in string format.
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
        this.dueDate = Parser.parseMyDate(deadline);
    }

    /**
     * Constructs a new deadline task with the specified description, completion status, and deadline.
     *
     * @param task     The description of the deadline task.
     * @param done     The completion status of the task.
     * @param deadline The due date of the task, in string format.
     */
    public Deadline(String task, boolean done, String deadline) {
        super(task, done);
        this.deadline = deadline;
        this.dueDate = Parser.parseMyDate(deadline);
    }

    /**
     * Retrieves the formatted deadline date as a string.
     * <p>
     *     If the date cannot be parsed, this method returns the raw string provided by the user.
     * </p>
     *
     * @return The formatted deadline date, or the raw string if the date is invalid.
     */
    public String getDeadline() {
        return this.dueDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.deadline);
    }

    /**
     * Retrieves the due date of the deadline task as a {@link LocalDateTime}.
     * <p>
     *     If the date cannot be parsed, this method returns {@link LocalDateTime#MAX}.
     * </p>
     *
     * @return The due date of the task, or {@link LocalDateTime#MAX} if the date is invalid.
     */
    public LocalDateTime getDeadlineDateTime() {

        return this.dueDate.orElse(LocalDateTime.MAX);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation creates and returns a new instance of the deadline task
     *     with its completion status set to done.
     * </p>
     */
    @Override
    public Deadline mark() {

        return new Deadline(this.getTask(), true, this.deadline);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation creates and returns a new instance of the deadline task
     *     with its completion status set to not done.
     * </p>
     */
    @Override
    public Deadline unmark() {

        return new Deadline(this.getTask(), false, this.deadline);
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * The string representation includes the prefix "[D]", followed by the task's
     * completion status, description, and formatted due date.
     * </p>
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {

        return String.format("[D]%s (by: %s)", super.toString(), this.getDeadline());
    }
}
