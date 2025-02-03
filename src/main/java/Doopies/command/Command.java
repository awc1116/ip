package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represent an abstract command in the doopies.Doopies application.
 * <p>
 *     Each command performs a specific action and may modify the notebook,
 *     interact with the user interface, or access storage.
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
     *     This method performs the action associated with the command. It may update the in-memory notebook,
     *     display messages to the user through the user interface, or interact with the storage system.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to interact with the user.
     * @param storage  The storage system used to persist or retrieve tasks.
     * @return The updated notebook after the command execution.
     */
    public abstract Notebook execute(Notebook notebook, Ui ui, Storage storage);
}
