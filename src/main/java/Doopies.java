import doopies.command.Command;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;
import doopies.util.Parser;

import java.io.IOException;
import java.util.NoSuchElementException;

public class Doopies {
    private static final String FILE_PATH = "./data/doopies.txt";
    private final Storage storage;
    private final Ui ui;
    private Notebook notebook;

    public Doopies(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.notebook = storage.load();
        } catch (IOException e) {
            this.ui.showMessage("Failed to load tasks. Starting with an empty notebook.");
            this.notebook = new Notebook();
        }
    }

    public static void main(String[] args) {
        new Doopies(FILE_PATH).run();
    }

    public void run() {
        this.ui.showWelcome();
        while (true) {
            try {
                String line = ui.readCommand();
                Command cmd = Parser.parseCommand(line);
                this.notebook = cmd.execute(this.notebook, this.ui, this.storage);
                if (cmd.isExit()) {
                    break;
                }
            } catch (NoSuchElementException ignored) {
                this.ui.showMessage("Please enter a command.");
            }
        }
    }
}