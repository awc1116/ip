package doopies.command;

import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FindCommandTest {
    private Ui ui;
    private Storage storage;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        String testFilePath = "./data/test_storage.txt";
        storage = new Storage(testFilePath);

        // Redirect System.out for testing output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testExecuteFindCommand() {
        Notebook notebook = new Notebook();
        notebook = notebook.add(new ToDo("read book"));
        notebook = notebook.add(new ToDo("write book"));
        notebook = notebook.add(new ToDo("play football"));

        FindCommand command = new FindCommand(new String[]{"find", "book"});
        command.execute(notebook, ui, storage);

        String expectedOutput = """
                ____________________________________________________________
                Here are the matching tasks in your list:
                1. [T][ ] read book
                2. [T][ ] write book
                ____________________________________________________________
                """;
        assertEquals(expectedOutput.strip(), outputStream.toString().strip());
    }
}
