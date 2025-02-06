package doopies.notebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a notebook that stores a list of tasks.
 * <p>
 * This class provides immutable operations to:
 * <ul>
 *     <li>Add, mark, unmark, or delete tasks.</li>
 *     <li>Retrieve tasks from the notebook.</li>
 *     <li>Search for tasks containing a specific keyword.</li>
 * </ul>
 * Each operation creates a new {@code Notebook} instance to ensure immutability.
 * </p>
 */
public class Notebook {
    private final List<Task> list;

    /**
     * Constructs an empty {@code Notebook}.
     */
    public Notebook() {
        this.list = List.of();
    }

    /**
     * Constructs a {@code Notebook} with the specified list of tasks.
     *
     * @param list The list of tasks to initialize the notebook with.
     */
    public Notebook(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds one or more tasks to the notebook.
     *
     * @param task The tasks to be added to the notebook.
     * @return A new {@code Notebook} instance containing the added tasks.
     */
    public Notebook add(Task... task) {
        List<Task> newList = new ArrayList<>(this.list);
        newList.addAll(Arrays.asList(task));
        return new Notebook(newList.stream().toList());
    }

    /**
     * Marks a task at the specified index as completed.
     *
     * @param idx The index of the task to mark as done (1-based index).
     * @return A new {@code Notebook} instance with the specified task marked as done.
     */
    public Notebook mark(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<>(this.list);
        newList.set(idx, this.list.get(idx).mark());
        return new Notebook(newList.stream().toList());
    }

    /**
     * Unmarks a task at the specified index as not done.
     *
     * @param idx The index of the task to unmark (1-based index).
     * @return A new {@code Notebook} instance with the specified task unmarked.
     */
    public Notebook unmark(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<>(this.list);
        newList.set(idx, this.list.get(idx).unmark());
        return new Notebook(newList.stream().toList());
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param idx The index of the task to delete (1-based index).
     * @return A new {@code Notebook} instance with the specified task removed.
     */
    public Notebook delete(int idx) {
        idx -= 1;
        List<Task> newList = new ArrayList<>(this.list);
        newList.remove(idx);
        return new Notebook(newList.stream().toList());
    }

    /**
     * Returns the total number of tasks in the notebook.
     *
     * @return The number of tasks in the notebook.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param idx The index of the task to retrieve (1-based index).
     * @return The {@code Task} at the specified index.
     */
    public Task getTask(int idx) {
        idx -= 1;
        return this.list.get(idx);
    }

    /**
     * Retrieves all tasks in the notebook as an immutable list.
     *
     * @return A list of all tasks in the notebook.
     */
    public List<Task> getAllTasks() {
        return this.list;
    }

    /**
     * Finds all tasks in the notebook that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of {@code Task} objects that contain the specified keyword in their description.
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

    /**
     * Returns a string representation of the notebook.
     * <p>
     * The string representation includes:
     * <ul>
     *     <li>A header message.</li>
     *     <li>A sequentially numbered list of all tasks in the notebook.</li>
     * </ul>
     * </p>
     *
     * @return A formatted string representing the notebook.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, this.list.get(i));
            str.append(temp);
        }
        return String.format("Here are the tasks in your list:\n%s",
                str.toString().stripTrailing());
    }
}
