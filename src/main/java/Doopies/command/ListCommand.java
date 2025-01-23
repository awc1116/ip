package Doopies.command;

import Doopies.notebook.Notebook;
import Doopies.userInterface.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui) {
        ui.showMessage(notebook.toString());
        return notebook;
    }
}
