package Doopies.command;

import Doopies.notebook.Notebook;
import Doopies.userInterface.Ui;

public class EndCommand extends Command {
    public EndCommand() {
        super(true);
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui) {
        ui.showEnding();
        return notebook;
    }
}
