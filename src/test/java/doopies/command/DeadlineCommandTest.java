package doopies.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public class DeadlineCommandTest {
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteValidDeadline() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        DeadlineCommand command = new DeadlineCommand(new String[]{"deadline return book", "/by 31/1/2025 2359"});
        notebook = command.execute(notebook, ui, storage);

        assertEquals(1, notebook.size());
        assertEquals("[D][ ] return book (by: Jan 31 2025, 11:59 pm)", notebook.getTask(1).toString());
    }

    @Test
    void testExecuteMissingDescription() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        DeadlineCommand command = new DeadlineCommand(new String[]{"deadline", "/by 31/1/2025 2359"});
        notebook = command.execute(notebook, ui, storage);

        assertEquals(0, notebook.size());
    }
}

