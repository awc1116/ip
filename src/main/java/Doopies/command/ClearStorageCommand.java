package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;

/**
 * Represents a command to clear the storage file while keeping the in-memory tasks intact.
 */
public class ClearStorageCommand extends Command {

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation clears all tasks stored in the storage file on the hard disk.
     *     It does not modify the in-memory task list. Upon successful clearing of the storage,
     *     a message is displayed to the user. If an error occurs during the clearing process,
     *     the error message is displayed to the user.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks (remains unchanged).
     * @param ui       The user interface used to display messages to the user.
     * @param storage  The storage system used to clear the stored tasks.
     * @return The unmodified notebook.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            storage.clear();
            ui.showMessage("Hard-disk storage have been cleared.");
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
        return notebook;
    }
}
