package doopies.notebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotebookTest {

    @Test
    void testAddTask() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));

        assertEquals(1, notebook.size());
        assertEquals("[T][ ] read book", notebook.getTask(1).toString());
    }

    @Test
    void testMarkTask() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));

        notebook = notebook.mark(1);
        assertTrue(notebook.getTask(1).isDone());
    }

    @Test
    void testUnmarkTask() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));

        notebook = notebook.mark(1).unmark(1);
        assertFalse(notebook.getTask(1).isDone());
    }

    @Test
    void testDeleteTask() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));

        assertEquals(1, notebook.size());
        notebook = notebook.delete(1);
        assertEquals(0, notebook.size());
    }

    @Test
    void testListTasks() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));
        notebook = notebook.add(new Deadline("return book", "31/1/2025 2359"));
        notebook = notebook.add(new Event("meeting", "24/1/2025 1400", "24/1/2025 1600"));

        String expected = """
                Here are the tasks in your list:
                1. [T][ ] read book
                2. [D][ ] return book (by: Jan 31 2025, 11:59 pm)
                3. [E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)
                """;

        assertEquals(expected.strip(), notebook.toString());
    }
}
