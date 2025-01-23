package Doopies.notebook;

import Doopies.util.DateFormat;

import java.time.LocalDateTime;
import java.util.Optional;

public class Deadline extends Task {
    private final String deadline;
    private final Optional<LocalDateTime> dueDate;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
        this.dueDate = DateFormat.parseMyDate(deadline);
    }

    public Deadline(String task, boolean done, String deadline) {
        super(task, done);
        this.deadline = deadline;
        this.dueDate = DateFormat.parseMyDate(deadline);
    }

    public String getDeadline() {
        return this.dueDate.map(date ->
                        date.format(DateFormat.OUTPUT_FORMAT.getFormatter()))
                .orElse(this.deadline);
    }

    public LocalDateTime getDeadlineDateTime() {
        return this.dueDate.orElse(LocalDateTime.MAX);
    }

    @Override
    public Deadline mark() {
        return new Deadline(this.getTask(), true, this.deadline);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(this.getTask(), false, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDeadline());
    }
}
