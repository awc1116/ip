package doopies.command;

import doopies.notebook.Deadline;
import doopies.notebook.Event;
import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ListCommandTest {
    private Storage storage;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() throws Exception {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();

        // Redirect System.out to capture command output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testExecuteListWithTasks() throws Exception {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        notebook = notebook.add(new ToDo("read book"));
        notebook = notebook.add(new Deadline("return book", "31/1/2025 2359"));
        notebook = notebook.add(new Event("meeting", "24/1/2025 1400", "24/1/2025 1600"));
        storage.save(notebook);

        ListCommand command = new ListCommand();
        command.execute(notebook, ui, storage);

        String output = outputStream.toString();
        assertTrue(output.contains("[T][ ] read book"));
        assertTrue(output.contains("[D][ ] return book (by: Jan 31 2025, 11:59 pm)"));
        assertTrue(output.contains("[E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)"));
    }
}

