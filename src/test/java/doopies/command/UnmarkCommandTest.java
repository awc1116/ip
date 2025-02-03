package doopies.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public class UnmarkCommandTest {
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteUnmark() throws IOException {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        notebook = notebook.add(new ToDo("read book"));
        notebook = notebook.mark(1);
        storage.save(notebook);

        UnmarkCommand command = new UnmarkCommand(new String[]{"unmark", "1"});
        notebook = command.execute(notebook, ui, storage);

        assertFalse(notebook.getTask(1).isDone());
    }
}

