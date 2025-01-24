package doopies.notebook;

public abstract class Task {
    private final String task;
    private final boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTask() {
        return this.task;
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
