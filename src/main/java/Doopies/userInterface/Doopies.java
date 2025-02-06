package doopies.userinterface;

import java.io.IOException;

import doopies.command.Command;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.util.Parser;

/**
 * Represents the core logic of the {@code Doopies} application.
 * <p>
 * The {@code Doopies} class handles:
 * <ul>
 *     <li>Managing task storage.</li>
 *     <li>Processing user commands.</li>
 *     <li>Interacting with the user interface.</li>
 * </ul>
 * This class is no longer the main entry point; instead, {@link Main} serves as the application's main launcher.
 * </p>
 */
public class Doopies {
    private static final String FILE_PATH = "./data/doopies.txt";
    private final Storage storage;
    private final Ui ui;
    private Notebook notebook;

    /**
     * Constructs a new {@code Doopies} instance with a specific file path for task storage.
     * <p>
     * Initializes the {@link Storage}, {@link Ui}, and attempts to load existing tasks from the file.
     * If loading fails, a new empty {@link Notebook} is created.
     * </p>
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

    /**
     * Processes the user's input and returns the corresponding response.
     * <p>
     * The method:
     * <ul>
     *     <li>Parses the user input to determine the appropriate {@link Command}.</li>
     *     <li>Executes the command, modifying the {@link Notebook} and interacting with storage as needed.</li>
     *     <li>Retrieves the latest response message from the {@link Ui}.</li>
     * </ul>
     * </p>
     *
     * @param input The user's input command.
     * @return The system's response message based on the executed command.
     */
    public String getResponse(String input) {
        Command cmd = Parser.parseCommand(input);
        this.notebook = cmd.execute(this.notebook, this.ui, this.storage);
        return ui.getLastMessage();
    }
}
