package doopies.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public class MarkCommandTest {
    private Storage storage;
    private final String testFilePath = "./data/test_storage.txt";

    @BeforeEach
    void setUp() throws IOException {
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteMark() throws IOException {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        notebook = notebook.add(new ToDo("read book"));
        storage.save(notebook);

        MarkCommand command = new MarkCommand(new String[]{"mark", "1"});
        notebook = command.execute(notebook, ui, storage);

        assertTrue(notebook.getTask(1).isDone());
    }
}

