package doopies.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public class ToDoCommandTest {
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteValidToDo() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        ToDoCommand command = new ToDoCommand(new String[]{"todo", "read book"});
        notebook = command.execute(notebook, ui, storage);

        assertEquals(1, notebook.size());
        assertEquals("[T][ ] read book", notebook.getTask(1).toString());
    }

    @Test
    void testExecuteMissingDescription() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        ToDoCommand command = new ToDoCommand(new String[]{"todo"});
        notebook = command.execute(notebook, ui, storage);

        assertEquals(0, notebook.size());
    }
}
