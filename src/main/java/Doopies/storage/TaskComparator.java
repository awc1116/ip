package Doopies.storage;

import Doopies.notebook.*;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
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
