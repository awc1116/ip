package doopies.command;

import doopies.notebook.Notebook;
import doopies.userinterface.Ui;

public abstract class Command {
    private final boolean exit;

    Command() {
        this.exit = false;
    }

    Command(boolean exit) {
        this.exit = exit;
    }

    public boolean isExit() {
        return this.exit;
    }

    public abstract Notebook execute(Notebook notebook, Ui ui);
}
