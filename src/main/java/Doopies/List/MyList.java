package Doopies.List;
import java.util.List;
import java.util.ArrayList;

public class MyList {
    private final List<Task> list;
    private static final String LINE = "_".repeat(60);

    public MyList() {
        this.list = List.<Task>of();
    }

    private MyList(List<Task> list) {
        this.list = list;
    }

    public MyList add(Task task) {
        List<Task> newList = new ArrayList<Task>(this.list);
        newList.add(task);
        return new MyList(newList.stream().toList());
    }

    public MyList mark(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<Task>(this.list);
        newList.set(idx, this.list.get(idx).mark());
        return new MyList(newList.stream().toList());
    }

    public MyList unmark(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<Task>(this.list);
        newList.set(idx, this.list.get(idx).unmark());
        return new MyList(newList.stream().toList());
    }

    public Task getTask(int idx) {
        idx -= 1;
        return this.list.get(idx);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, this.list.get(i));
            str.append(temp);
        }
        return String.format("""
                %s
                Here are the tasks in your list:
                %s%s
                """,
                LINE,
                str,
                LINE);

    }
}
