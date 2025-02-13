package doopies.command;

import doopies.exception.IndexOutOfBoundException;
import doopies.exception.InvalidTaskTypeException;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to mark a task in the {@link Notebook} as completed.
 * <p>
 * This command:
 * <ul>
 *     <li>Retrieves the task index from user input.</li>
 *     <li>Marks the specified task as done.</li>
 *     <li>Updates the {@link Storage} system by saving the modified notebook.</li>
 *     <li>Displays a confirmation message upon successful marking.</li>
 * </ul>
 * If the provided index is invalid (out of bounds or not a valid integer), an appropriate error message is displayed.
 * </p>
 */
public class MarkCommand extends Command {
    private final String[] cmd;

    /**
     * Constructs a {@code MarkCommand} with the specified parsed input.
     *
     * @param cmd The parsed input command containing the mark action and the task index.
     */
    public MarkCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    /**
     * Executes the command to mark a task as completed in the notebook.
     * <p>
     * This method:
     * <ul>
     *     <li>Parses the task index from user input.</li>
     *     <li>Checks if the index is within valid bounds.</li>
     *     <li>Marks the specified task as done.</li>
     *     <li>Persists the updated notebook to the {@link Storage} system.</li>
     *     <li>Displays a confirmation message to the user.</li>
     * </ul>
     * If the provided index is invalid or not an integer, an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} component used to interact with the user.
     * @param storage  The {@link Storage} system responsible for saving the updated notebook.
     * @return The updated {@link Notebook} with the specified task marked as done.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            int idx = parseIndex(this.cmd, notebook);

            notebook = notebook.mark(idx);
            saveNotebook(notebook, storage, ui);

            String message = String.format("Alright! I've marked this task as done:\n\t%s",
                    notebook.getTask(idx));

            ui.showMessage(message);
        } catch (IndexOutOfBoundException
                 | InvalidTaskTypeException e) {
            ui.showMessage(e.getMessage());
        }
        return notebook;
    }
}
