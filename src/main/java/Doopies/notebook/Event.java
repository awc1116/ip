package doopies.notebook;

import doopies.util.DateFormat;
import doopies.util.Parser;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents an event task in the Doopies application.
 * <p>
 *     An event task contains a description, a start time, and an end time.
 *     It can be marked as done or not done and supports a string representation
 *     that includes its time range.
 * </p>
 */
public class Event extends Task {
    /** The raw string representation of the start time provided by the user. */
    private final String start;

    /** The raw string representation of the end time provided by the user. */
    private final String end;

    /** The parsed start time as a {@link LocalDateTime}, if successfully parsed. */
    private final Optional<LocalDateTime> startDate;

    /** The parsed end time as a {@link LocalDateTime}, if successfully parsed. */
    private final Optional<LocalDateTime> endDate;

    /**
     * Constructs a new event task with the specified description, start time, and end time.
     *
     * @param task  The description of the event task.
     * @param start The start time of the event, in string format.
     * @param end   The end time of the event, in string format.
     */
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
        this.startDate = Parser.parseMyDate(start);
        this.endDate = Parser.parseMyDate(end);
    }

    /**
     * Constructs a new event task with the specified description, completion status, start time, and end time.
     *
     * @param task  The description of the event task.
     * @param done  The completion status of the event task.
     * @param start The start time of the event, in string format.
     * @param end   The end time of the event, in string format.
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
     *     If the start time cannot be parsed, this method returns the raw string provided by the user.
     * </p>
     *
     * @return The formatted start time, or the raw string if the date is invalid.
     */
    public String getStart() {
        return this.startDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.start);
    }

    /**
     * Retrieves the formatted end time of the event.
     * <p>
     *     If the end time cannot be parsed, this method returns the raw string provided by the user.
     * </p>
     *
     * @return The formatted end time, or the raw string if the date is invalid.
     */
    public String getEnd() {
        return this.endDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.end);
    }

    /**
     * Retrieves the start time of the event as a {@link LocalDateTime}.
     * <p>
     *     If the start time cannot be parsed, this method returns {@link LocalDateTime#MAX}.
     * </p>
     *
     * @return The start time of the event, or {@link LocalDateTime#MAX} if the date is invalid.
     */
    public LocalDateTime getStartDateTime() {
        return this.startDate.orElse(LocalDateTime.MAX);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation creates and returns a new instance of the event task
     *     with its completion status set to done.
     * </p>
     */
    @Override
    public Event mark() {
        return new Event(this.getTask(), true, this.start, this.end);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation creates and returns a new instance of the event task
     *     with its completion status set to not done.
     * </p>
     */
    @Override
    public Event unmark() {
        return new Event(this.getTask(), false, this.start, this.end);
    }

    /**
     * Returns a string representation of the event task.
     * <p>
     *     The string representation includes the prefix "[E]", followed by the task's
     *     completion status, description, formatted start time, and formatted end time.
     * </p>
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.getStart(), this.getEnd());
    }
}
