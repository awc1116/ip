package Doopies.command;

import Doopies.Exception.UnknownCommandException;
import Doopies.notebook.Notebook;
import Doopies.userInterface.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super();
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui) {
        try {
            throw new UnknownCommandException("OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        } catch (UnknownCommandException e) {
            ui.showMessage(e.getMessage());
        }
        return notebook;
    }
}
