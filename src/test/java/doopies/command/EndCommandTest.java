package doopies.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public class EndCommandTest {
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteEnd() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        EndCommand command = new EndCommand();
        Notebook result = command.execute(notebook, ui, storage);

        assertEquals(notebook, result);
    }
}

