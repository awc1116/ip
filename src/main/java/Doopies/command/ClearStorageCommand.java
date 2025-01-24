package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;

public class ClearStorageCommand extends Command {
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            storage.clear();
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
        return notebook;
    }
}
