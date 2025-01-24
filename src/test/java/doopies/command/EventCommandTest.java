package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class EventCommandTest {
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteValidEvent() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        EventCommand command = new EventCommand(new String[]{"event meeting", "/from 24/1/2025 1400", "/to 24/1/2025 1600"});
        notebook = command.execute(notebook, ui, storage);

        assertEquals(1, notebook.size());
        assertEquals("[E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)", notebook.getTask(1).toString());
    }

    @Test
    void testExecuteMissingDescription() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        EventCommand command = new EventCommand(new String[]{"event", "/from 24/1/2025 1400", "/to 24/1/2025 1600"});
        notebook = command.execute(notebook, ui, storage);

        assertEquals(0, notebook.size());
    }
}
