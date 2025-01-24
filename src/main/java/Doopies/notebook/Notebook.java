package doopies.notebook;

import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private final List<Task> list;

    public Notebook() {
        this.list = List.of();
    }

    public Notebook(List<Task> list) {
        this.list = list;
    }

    public Notebook add(Task task) {
        List<Task> newList = new ArrayList<>(this.list);
        newList.add(task);
        return new Notebook(newList.stream().toList());
    }

    public Notebook mark(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<>(this.list);
        newList.set(idx, this.list.get(idx).mark());
        return new Notebook(newList.stream().toList());
    }

    public Notebook unmark(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<>(this.list);
        newList.set(idx, this.list.get(idx).unmark());
        return new Notebook(newList.stream().toList());
    }

    public Notebook delete(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<>(this.list);
        newList.remove(idx);
        return new Notebook(newList.stream().toList());
    }

    public Task getTask(int idx) {
        idx -= 1;
        return this.list.get(idx);
    }

    public int size() {
        return this.list.size();
    }

    public List<Task> getAllTasks() {
        return this.list;
    }

    /**
     * Finds all tasks in the notebook that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks that contain the specified keyword in their description.
     */
    public List<Task> find(String keyword) {
        List<Task> newList = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getTask().contains(keyword)) {
                newList.add(task);
            }
        }
        return newList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, this.list.get(i));
            str.append(temp);
        }
        return String.format("Here are the tasks in your list:\n%s", str.toString().stripTrailing());

    }
}
