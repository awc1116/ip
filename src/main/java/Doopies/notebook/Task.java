package Doopies.notebook;

abstract class Task {
    protected final String task;
    private final boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    abstract Task mark();

    abstract Task unmark();

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.done ? "X" : " ",
                this.task);
    }
}
