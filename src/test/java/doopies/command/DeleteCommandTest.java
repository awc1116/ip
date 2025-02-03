package doopies.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public class DeleteCommandTest {
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteDelete() throws IOException {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        notebook = notebook.add(new ToDo("read book"));
        storage.save(notebook);

        DeleteCommand command = new DeleteCommand(new String[]{"delete", "1"});
        notebook = command.execute(notebook, ui, storage);

        assertEquals(0, notebook.size());
    }
}

