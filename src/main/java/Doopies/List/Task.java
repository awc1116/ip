package Doopies.List;

public class Task {
    private final String task;
    private final boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    private Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public Task mark() {
        return new Task(this.task, true);
    }

    public Task unmark() {
        return new Task(this.task, false);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.done ? "X" : " ",
                this.task);
    }
}
