package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UnknownCommandTest {
    private Storage storage;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() throws Exception {
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);
        storage.clear();

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testExecuteUnknownCommand() {
        Notebook notebook = new Notebook();
        Ui ui = new Ui();

        UnknownCommand command = new UnknownCommand();
        command.execute(notebook, ui, storage);

        String output = outputStream.toString();
        assertTrue(output.contains("OOPS!!! I'm sorry, but I don't know what that means :-("));
    }
}

