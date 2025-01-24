package doopies.command;

import doopies.notebook.Notebook;
import doopies.userinterface.Ui;

public class EndCommand extends Command {
    public EndCommand() {
        super(true);
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui) {
        ui.showEnding();
        ui.closeUi();
        return notebook;
    }
}
