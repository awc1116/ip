package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to display all tasks currently in the notebook.
 * <p>
 *     This command retrieves the list of tasks from the notebook and displays them to the user
 *     through the user interface. The notebook and storage are not modified by this command.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {

        super();
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation retrieves all tasks from the notebook and displays them to the user.
     *     If the notebook is empty, an appropriate message is shown. The command does not modify
     *     the notebook or storage.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to display the list of tasks to the user.
     * @param storage  The storage system (not used in this command).
     * @return The unmodified notebook.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        ui.showMessage(notebook.toString());
        return notebook;
    }
}
