package Doopies.notebook;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public ToDo mark() {
        return new ToDo(this.getTask(), true);
    }

    @Override
    public ToDo unmark() {
        return new ToDo(this.getTask(), false);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
