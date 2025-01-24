package doopies.util;

import doopies.notebook.Deadline;
import doopies.notebook.Event;
import doopies.notebook.Task;
import doopies.notebook.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskComparatorTest {

    @Test
    void testCompareDifferentTypes() {
        TaskComparator comparator = new TaskComparator();

        Task todo = new ToDo("read book");
        Task deadline = new Deadline("return book", "31/1/2025 2359");
        Task event = new Event("meeting", "24/1/2025 1400", "24/1/2025 1600");

        assertTrue(comparator.compare(todo, deadline) < 0);
        assertTrue(comparator.compare(deadline, event) > 0);
    }

    @Test
    void testCompareSameTypeAlphabetical() {
        TaskComparator comparator = new TaskComparator();

        Task todo1 = new ToDo("a book");
        Task todo2 = new ToDo("z book");

        assertTrue(comparator.compare(todo1, todo2) < 0);
    }

    @Test
    void testCompareSameTypeByDate() {
        TaskComparator comparator = new TaskComparator();

        Task deadline1 = new Deadline("submit report", "30/1/2025 1200");
        Task deadline2 = new Deadline("return book", "31/1/2025 2359");

        assertTrue(comparator.compare(deadline1, deadline2) < 0);
    }
}
