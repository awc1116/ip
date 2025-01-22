package Doopies.notebook;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public Deadline(String task, boolean done, String deadline) {
        super(task, done);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
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
        return String.format("[D]%s (%s)", super.toString(), this.deadline);
    }
}
