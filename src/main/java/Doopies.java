import Doopies.storage.*;
import Doopies.notebook.*;
import Doopies.userInterface.*;
import Doopies.command.*;

import java.io.IOException;
import java.util.NoSuchElementException;

public class Doopies {
    private final Storage storage;
    private final Ui ui;
    private Notebook notebook;
    private static final String FILE_PATH = "./data/doopies.txt";

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

    public void run() {
        this.ui.showWelcome();
        while (true) {
            try {
                String line = ui.readCommand();
                Command cmd = Parser.parse(line);
                this.notebook = cmd.execute(this.notebook, this.ui);
                this.storage.save(this.notebook);
                if (cmd.isExit()) {
                    this.ui.closeUi();
                    break;
                }
            } catch (IOException e) {
                this.ui.showMessage(e.getMessage());
            } catch (NoSuchElementException ignored) {
                this.ui.showMessage("Please enter a command.");
            }
        }
    }

    public static void main(String[] args) {
        new Doopies(FILE_PATH).run();
    }
}