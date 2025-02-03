package doopies.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.notebook.Deadline;
import doopies.notebook.Event;
import doopies.notebook.Notebook;
import doopies.notebook.ToDo;

public class StorageTest {
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        Path path = Paths.get(testFilePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    void testSaveAndLoad() throws IOException {
        Notebook notebook = new Notebook();

        notebook = notebook.add(new ToDo("read book"));
        notebook = notebook.add(new Deadline("return book", "31/1/2025 2359"));
        notebook = notebook.add(new Event("meeting", "24/1/2025 1400", "24/1/2025 1600"));

        storage.save(notebook);

        Notebook loadedNotebook = storage.load();

        assertEquals(3, loadedNotebook.size());
        assertEquals("[T][ ] read book", loadedNotebook.getTask(1).toString());
        assertEquals("[E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)",
                loadedNotebook.getTask(2).toString());
        assertEquals("[D][ ] return book (by: Jan 31 2025, 11:59 pm)", loadedNotebook.getTask(3).toString());
    }

    @Test
    void testClearStorage() throws IOException {
        Notebook notebook = new Notebook();

        notebook = notebook.add(new ToDo("read book"));
        storage.save(notebook);

        storage.clear();

        Notebook loadedNotebook = storage.load();
        assertEquals(0, loadedNotebook.size());
    }
}

