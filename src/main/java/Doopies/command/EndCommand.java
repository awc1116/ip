package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to terminate the {@code Doopies} application.
 * <p>
 * This command:
 * <ul>
 *     <li>Signals the application to exit.</li>
 *     <li>Displays a farewell message to the user.</li>
 *     <li>Closes any UI resources if necessary.</li>
 *     <li>Leaves the in-memory {@link Notebook} unchanged.</li>
 *     <li>Does not modify the {@link Storage} system.</li>
 * </ul>
 * </p>
 */
public class EndCommand extends Command {

    /**
     * Constructs an {@code EndCommand} that signals the application to terminate.
     */
    public EndCommand() {
        super(true);
    }

    /**
     * Executes the command to terminate the application.
     * <p>
     * This method:
     * <ul>
     *     <li>Displays a farewell message to the user via {@link Ui}.</li>
     *     <li>Leaves the in-memory {@link Notebook} unchanged.</li>
     *     <li>Does not modify the {@link Storage} system.</li>
     * </ul>
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks (remains unchanged).
     * @param ui       The {@link Ui} component used to display the farewell message and close resources.
     * @param storage  The {@link Storage} system (not used in this command).
     * @return The unmodified {@link Notebook}.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        ui.showEnding();
        return notebook;
    }
}
