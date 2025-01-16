package Doopies.notebook;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    private Event(String task, boolean done, String start, String end) {
        super(task, done);
        this.start = start;
        this.end = end;
    }

    @Override
    public Event mark() {
        return new Event(super.task, true, this.start, this.end);
    }

    @Override
    public Event unmark() {
        return new Event(super.task, false, this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s %s)", super.toString(), this.start, this.end);
    }
}
