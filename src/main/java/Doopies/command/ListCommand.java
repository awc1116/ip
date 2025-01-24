package doopies.command;

import doopies.notebook.Notebook;
import doopies.userinterface.Ui;

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
