package Doopies.notebook;

import Doopies.util.DateFormat;

import java.time.LocalDateTime;
import java.util.Optional;

public class Event extends Task {
    private final String start;
    private final String end;
    private final Optional<LocalDateTime> startDate;
    private final Optional<LocalDateTime> endDate;

    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
        this.startDate = DateFormat.parseMyDate(start);
        this.endDate = DateFormat.parseMyDate(end);
    }

    public Event(String task, boolean done, String start, String end) {
        super(task, done);
        this.start = start;
        this.end = end;
        this.startDate = DateFormat.parseMyDate(start);
        this.endDate = DateFormat.parseMyDate(end);
    }

    public String getStart() {
        return this.startDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.start);
    }

    public String getEnd() {
        return this.endDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.end);
    }

    public LocalDateTime getStartDateTime() {
        return this.startDate.orElse(LocalDateTime.MAX);
    }

    @Override
    public Event mark() {
        return new Event(this.getTask(), true, this.start, this.end);
    }

    @Override
    public Event unmark() {
        return new Event(this.getTask(), false, this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.getStart(), this.getEnd());
    }
}
