package doopies.command;

import java.io.IOException;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * A command that clears the storage file while keeping the in-memory task list unchanged.
 * <p>
 * This command deletes all tasks stored in the persistent storage file on the hard disk.
 * However, it does not modify the current in-memory {@link Notebook} containing the list of tasks.
 * </p>
 * <p>
 * If the storage is successfully cleared, a confirmation message is displayed to the user.
 * If an error occurs during the clearing process, the appropriate error message is shown.
 * </p>
 */
public class ClearStorageCommand extends Command {

    /**
     * Constructs a {@code ClearStorageCommand}.
     */
    public ClearStorageCommand() {
        super();
    }

    /**
     * Executes the command to clear the storage file while keeping the in-memory task list intact.
     * <p>
     * This implementation removes all tasks stored in the hard disk storage file but does not affect
     * the current in-memory {@link Notebook}. Upon successful clearing, a message is displayed to the user.
     * If an error occurs while clearing the storage, an error message is shown instead.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks (remains unchanged).
     * @param ui       The {@link Ui} component used to display messages to the user.
     * @param storage  The {@link Storage} system responsible for clearing the stored tasks.
     * @return A new, empty {@link Notebook} instance.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            storage.clear();
            ui.showMessage("Whole list have been cleared.");
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
        return new Notebook();
    }
}
