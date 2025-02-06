package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents an abstract command in the {@code Doopies} application.
 * <p>
 * Each command defines an action that may:
 * <ul>
 *     <li>Modify the in-memory {@link Notebook} (e.g., add, delete, mark tasks).</li>
 *     <li>Interact with the {@link Ui} to display messages to the user.</li>
 *     <li>Access the {@link Storage} system to save or retrieve tasks.</li>
 * </ul>
 * Commands extending this class must implement the {@link #execute(Notebook, Ui, Storage)} method.
 * </p>
 */
public abstract class Command {
    private final boolean exit;

    protected Command() {
        this.exit = false;
    }

    protected Command(boolean exit) {
        this.exit = exit;
    }

    /**
     * Checks whether this command signals the application to terminate.
     *
     * @return {@code true} if the command signals the application to terminate; {@code false} otherwise.
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Executes the command.
     * <p>
     * This method performs the action associated with the command, which may involve:
     * <ul>
     *     <li>Updating the in-memory {@link Notebook} with new tasks or modifications.</li>
     *     <li>Displaying messages to the user through the {@link Ui}.</li>
     *     <li>Persisting changes to the {@link Storage} system.</li>
     * </ul>
     * Each subclass must implement this method with its specific behavior.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} used to interact with the user.
     * @param storage  The {@link Storage} system responsible for saving or retrieving tasks.
     * @return The updated {@link Notebook} after executing the command.
     */
    public abstract Notebook execute(Notebook notebook, Ui ui, Storage storage);
}
