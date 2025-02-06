package doopies.notebook;

import java.time.LocalDateTime;
import java.util.Optional;

import doopies.util.DateFormat;
import doopies.util.Parser;

/**
 * Represents an event task in the {@code Doopies} application.
 * <p>
 * An event task contains:
 * <ul>
 *     <li>A description of the task.</li>
 *     <li>A start time and an end time, stored as {@code String} values and optionally as {@link LocalDateTime}.</li>
 *     <li>A completion status (marked as done or not done).</li>
 * </ul>
 * The start and end times can be formatted for display and parsed for date-based operations.
 * </p>
 */
public class Event extends Task {
    private final String start;
    private final String end;
    private final Optional<LocalDateTime> startDate;
    private final Optional<LocalDateTime> endDate;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     *
     * @param task  The description of the event task.
     * @param start The start time of the event in string format.
     * @param end   The end time of the event in string format.
     */
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
        this.startDate = Parser.parseMyDate(start);
        this.endDate = Parser.parseMyDate(end);
    }

    /**
     * Constructs a new {@code Event} task with the specified description, completion status, start time, and end time.
     *
     * @param task  The description of the event task.
     * @param done  The completion status of the event task.
     * @param start The start time of the event in string format.
     * @param end   The end time of the event in string format.
     */
    public Event(String task, boolean done, String start, String end) {
        super(task, done);
        this.start = start;
        this.end = end;
        this.startDate = Parser.parseMyDate(start);
        this.endDate = Parser.parseMyDate(end);
    }

    /**
     * Retrieves the formatted start time of the event.
     * <p>
     * If the start time is successfully parsed, it is returned in a formatted string.
     * Otherwise, the original raw string provided by the user is returned.
     * </p>
     *
     * @return The formatted start time, or the raw string if parsing fails.
     */
    public String getStart() {
        return this.startDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.start);
    }

    /**
     * Retrieves the formatted end time of the event.
     * <p>
     * If the end time is successfully parsed, it is returned in a formatted string.
     * Otherwise, the original raw string provided by the user is returned.
     * </p>
     *
     * @return The formatted end time, or the raw string if parsing fails.
     */
    public String getEnd() {
        return this.endDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.end);
    }

    /**
     * Retrieves the start time of the event as a {@link LocalDateTime}.
     * <p>
     * If the start time cannot be parsed, this method returns {@link LocalDateTime#MAX}.
     * </p>
     *
     * @return The parsed start time as a {@link LocalDateTime}, or {@link LocalDateTime#MAX} if parsing fails.
     */
    public LocalDateTime getStartDateTime() {
        return this.startDate.orElse(LocalDateTime.MAX);
    }

    /**
     * Marks this event task as completed.
     * <p>
     * This method creates and returns a new instance of the event task with its completion status set to done.
     * </p>
     *
     * @return A new {@code Event} instance marked as done.
     */
    @Override
    public Event mark() {
        return new Event(this.getTask(), true, this.start, this.end);
    }

    /**
     * Unmarks this event task (sets it as not done).
     * <p>
     * This method creates and returns a new instance of the event task with its completion status set to not done.
     * </p>
     *
     * @return A new {@code Event} instance marked as not done.
     */
    @Override
    public Event unmark() {
        return new Event(this.getTask(), false, this.start, this.end);
    }

    /**
     * Returns a string representation of the event task.
     * <p>
     * The string representation includes:
     * <ul>
     *     <li>The prefix "[E]" to indicate an event task.</li>
     *     <li>The task's completion status.</li>
     *     <li>The task description.</li>
     *     <li>The formatted start time and end time.</li>
     * </ul>
     * </p>
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.getStart(), this.getEnd());
    }
}
