package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to display all tasks currently in the {@link Notebook}.
 * <p>
 * This command:
 * <ul>
 *     <li>Retrieves all tasks stored in the {@link Notebook}.</li>
 *     <li>Displays the list of tasks to the user via the {@link Ui}.</li>
 *     <li>Does not modify the {@link Notebook} or {@link Storage} system.</li>
 * </ul>
 * If the notebook is empty, an appropriate message is displayed.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand}.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command to display all tasks in the notebook.
     * <p>
     * This method:
     * <ul>
     *     <li>Retrieves all tasks from the {@link Notebook}.</li>
     *     <li>Formats and displays them through the {@link Ui}.</li>
     *     <li>If the notebook is empty, an appropriate message is shown.</li>
     *     <li>Does not modify the {@link Notebook} or {@link Storage} system.</li>
     * </ul>
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} component used to display the list of tasks to the user.
     * @param storage  The {@link Storage} system (not used in this command).
     * @return The unmodified {@link Notebook}.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        ui.showMessage(notebook.toString());
        return notebook;
    }
}
