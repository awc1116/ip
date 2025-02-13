package doopies.command;

import doopies.exception.IndexOutOfBoundException;
import doopies.exception.InvalidTaskTypeException;
import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to delete a task from the {@link Notebook}.
 * <p>
 * This command:
 * <ul>
 *     <li>Retrieves the task index from the user input.</li>
 *     <li>Removes the task at the specified index from the {@link Notebook}.</li>
 *     <li>Updates the {@link Storage} system by saving the modified notebook.</li>
 *     <li>Displays a confirmation message upon successful deletion.</li>
 * </ul>
 * If the provided index is invalid (out of bounds or not a valid integer), an appropriate error message is displayed.
 * </p>
 */
public class DeleteCommand extends Command {
    private final String[] cmd;

    /**
     * Constructs a {@code DeleteCommand} with the specified parsed input.
     *
     * @param cmd The parsed input command containing the delete action and the task index.
     */
    public DeleteCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    /**
     * Executes the command to delete a task from the notebook.
     * <p>
     * This method:
     * <ul>
     *     <li>Parses the task index from user input.</li>
     *     <li>Checks if the index is within valid bounds.</li>
     *     <li>Retrieves the task to be deleted for confirmation.</li>
     *     <li>Removes the task from the {@link Notebook}.</li>
     *     <li>Persists the updated notebook to the {@link Storage} system.</li>
     *     <li>Displays a confirmation message to the user.</li>
     * </ul>
     * If the provided index is invalid or not an integer, an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} used to interact with the user.
     * @param storage  The {@link Storage} system responsible for saving the updated notebook.
     * @return The updated {@link Notebook} after removing the task.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            int idx = parseIndex(this.cmd, notebook);

            Task task = notebook.getTask(idx);
            notebook = notebook.delete(idx);
            saveNotebook(notebook, storage, ui);

            String message = String.format("""
                            Noted. I've removed this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    task, notebook.size());

            ui.showMessage(message);
        } catch (IndexOutOfBoundException
                 | InvalidTaskTypeException e) {
            ui.showMessage(e.getMessage());
        }
        return notebook;
    }
}
