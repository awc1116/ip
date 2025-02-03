package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to terminate the doopies.Doopies application.
 * <p>
 * This command signals the application to exit by displaying a farewell message to the user and closing the user interface.
 * The in-memory notebook remains unchanged, and no modifications are made to the storage system.
 * </p>
 */
public class EndCommand extends Command {

    /**
     * Constructs an EndCommand that signals the application to terminate.
     */
    public EndCommand() {

        super(true);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation displays a farewell message to the user, closes the user interface,
     *     and leaves the in-memory notebook unchanged. The storage system is not modified.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks (remains unchanged).
     * @param ui       The user interface used to display the farewell message and close resources.
     * @param storage  The storage system (not used in this command).
     * @return The unmodified notebook.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        ui.showEnding();
        ui.closeUi();
        return notebook;
    }
}
