package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public abstract class Command {
    private final boolean exit;

    public Command() {
        this.exit = false;
    }

    public Command(boolean exit) {
        this.exit = exit;
    }

    public boolean isExit() {
        return this.exit;
    }

    public abstract Notebook execute(Notebook notebook, Ui ui, Storage storage);
}
