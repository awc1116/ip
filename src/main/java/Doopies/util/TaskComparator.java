package doopies.util;

import java.util.Comparator;

import doopies.notebook.Deadline;
import doopies.notebook.Event;
import doopies.notebook.Task;
import doopies.notebook.ToDo;

/**
 * A comparator for sorting tasks in the doopies.userinterface.Doopies application.
 * <p>
 * The {@code TaskComparator} sorts tasks based on their type and specific criteria:
 * <ul>
 *     <li>{@link ToDo} tasks are sorted alphabetically by their description.</li>
 *     <li>{@link Deadline} tasks are sorted by their due date.</li>
 *     <li>{@link Event} tasks are sorted by their start date.</li>
 *     <li>Tasks of different types are sorted in the following order:
 *         <ol>
 *             <li>{@link ToDo}</li>
 *             <li>{@link Deadline}</li>
 *             <li>{@link Event}</li>
 *         </ol>
 *     </li>
 * </ul>
 * </p>
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares two {@link Task} objects for order.
     * <p>
     * The comparison rules are:
     * <ul>
     *     <li>If both tasks are {@link ToDo}, they are compared alphabetically by description.</li>
     *     <li>If one task is a {@link ToDo}, it is considered smaller than a {@link Deadline} or {@link Event}.</li>
     *     <li>If both tasks are {@link Deadline}, they are compared by their due dates.</li>
     *     <li>If both tasks are {@link Event}, they are compared by their start dates.</li>
     *     <li>If one task is a {@link Deadline} and the other is an {@link Event}, they are compared
     *     by the deadline's due date and the event's start date.</li>
     * </ul>
     * If none of the above rules apply, tasks are compared alphabetically by description as a fallback.
     * </p>
     *
     * @param t1 The first task to compare.
     * @param t2 The second task to compare.
     * @return A negative integer, zero, or a positive integer as {@code t1} is less than, equal to,
     *     or greater than {@code t2}.
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDo && t2 instanceof ToDo) {
            return t1.getTask().compareToIgnoreCase(t2.getTask());
        } else if (t1 instanceof ToDo) {
            return -1;
        } else if (t2 instanceof ToDo) {
            return 1;
        } else if (t1 instanceof Deadline && t2 instanceof Deadline) {
            return ((Deadline) t1).getDeadlineDateTime()
                    .compareTo(((Deadline) t2).getDeadlineDateTime());
        } else if (t1 instanceof Event && t2 instanceof Event) {
            return ((Event) t1).getStartDateTime()
                    .compareTo(((Event) t2).getStartDateTime());
        } else if (t1 instanceof Deadline && t2 instanceof Event) {
            return ((Deadline) t1).getDeadlineDateTime()
                    .compareTo(((Event) t2).getStartDateTime());
        } else if (t1 instanceof Event && t2 instanceof Deadline) {
            return ((Event) t1).getStartDateTime()
                    .compareTo(((Deadline) t2).getDeadlineDateTime());
        }
        return t1.getTask().compareToIgnoreCase(t2.getTask());
    }
}
