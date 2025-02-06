package doopies.notebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

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

    @Test
    void testFindTasks() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));
        notebook = notebook.add(new ToDo("write book"));
        notebook = notebook.add(new ToDo("play football"));

        List<Task> result = notebook.find("book");

        assertEquals(2, result.size());
        assertEquals("[T][ ] read book", result.get(0).toString());
        assertEquals("[T][ ] write book", result.get(1).toString());
    }

    @Test
    void testFindNoMatch() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));
        notebook = notebook.add(new ToDo("write book"));

        List<Task> result = notebook.find("football");

        assertEquals(0, result.size());
    }
}
