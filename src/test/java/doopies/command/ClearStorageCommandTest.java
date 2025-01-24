package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClearStorageCommandTest {
    private Storage storage;
    private final String testFilePath = "./data/test_storage.txt";

    @BeforeEach
    void setUp() throws IOException {
        storage = new Storage(testFilePath);
        storage.clear();
    }

    @Test
    void testExecuteClear() throws IOException {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        notebook = notebook.add(new doopies.notebook.ToDo("read book"));
        storage.save(notebook);

        ClearStorageCommand command = new ClearStorageCommand();
        command.execute(notebook, ui, storage);

        notebook = storage.load();

        assertEquals(0, notebook.size());
        assertEquals(0, Files.readAllLines(Path.of(testFilePath)).size());
    }
}

