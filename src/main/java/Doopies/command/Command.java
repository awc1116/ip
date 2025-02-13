package doopies.command;

import java.io.IOException;
import java.util.Arrays;

import doopies.exception.IndexOutOfBoundException;
import doopies.exception.InvalidTaskTypeException;
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

    protected int parseIndex(String[] cmd, Notebook notebook) throws
            IndexOutOfBoundException, InvalidTaskTypeException {
        if (cmd.length < 2) {
            throw new InvalidTaskTypeException("No index provided.");
        }

        int idx;
        try {
            idx = Integer.parseInt(cmd[1]);
        } catch (NumberFormatException e) {
            throw new IndexOutOfBoundException(cmd[1]);
        }

        if (idx < 1 || idx > notebook.size()) {
            throw new IndexOutOfBoundException(String.valueOf(idx));
        }

        return idx;
    }

    protected void saveNotebook(Notebook notebook, Storage storage, Ui ui) {
        try {
            storage.save(notebook);
        } catch (IOException e) {
            ui.showMessage("Failed to save tasks: " + e.getMessage());
        }
    }

    protected String translate(String[] cmd) {
        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }
}
