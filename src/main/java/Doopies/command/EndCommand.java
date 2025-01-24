package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

public class EndCommand extends Command {
    public EndCommand() {
        super(true);
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        ui.showEnding();
        ui.closeUi();
        return notebook;
    }
}
