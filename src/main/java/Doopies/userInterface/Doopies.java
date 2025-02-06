package doopies.userinterface;

import java.io.IOException;

import doopies.command.Command;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.util.Parser;

/**
 * Represents the main entry point for the doopies.userinterface.Doopies application.
 * Handles the initialization of the application, execution of commands, and user interaction.
 */
public class Doopies {
    private static final String FILE_PATH = "./data/doopies.txt";
    private final Storage storage;
    private final Ui ui;
    private Notebook notebook;

    /**
     * Constructs a new doopies.userinterface.Doopies application instance with the specific file path for task storage.
     */
    public Doopies() {
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        try {
            this.notebook = storage.load();
        } catch (IOException e) {
            this.notebook = new Notebook();
        }
    }

    public String getResponse(String input) {
        Command cmd = Parser.parseCommand(input);
        this.notebook = cmd.execute(this.notebook, this.ui, this.storage);
        return ui.getLastMessage();
    }
}
