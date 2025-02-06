package doopies.command;

import java.io.IOException;

import doopies.exception.IndexOutOfBoundException;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to unmark a task as not done in the {@link Notebook}.
 * <p>
 * This command:
 * <ul>
 *     <li>Retrieves the task index from user input.</li>
 *     <li>Marks the specified task as not done.</li>
 *     <li>Updates the {@link Storage} system by saving the modified notebook.</li>
 *     <li>Displays a confirmation message upon successful unmarking.</li>
 * </ul>
 * If the provided index is invalid (out of bounds or not a valid integer), an appropriate error message is displayed.
 * </p>
 */
public class UnmarkCommand extends Command {
    private final String[] cmd;

    /**
     * Constructs an {@code UnmarkCommand} with the specified parsed input.
     *
     * @param cmd The parsed command arguments containing the unmark action and the task index.
     */
    public UnmarkCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    /**
     * Executes the command to unmark a task as not done in the notebook.
     * <p>
     * This method:
     * <ul>
     *     <li>Parses the task index from user input.</li>
     *     <li>Checks if the index is within valid bounds.</li>
     *     <li>Marks the specified task as not done.</li>
     *     <li>Persists the updated notebook to the {@link Storage} system.</li>
     *     <li>Displays a confirmation message to the user.</li>
     * </ul>
     * If the provided index is invalid or not an integer, an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} component used to interact with the user.
     * @param storage  The {@link Storage} system responsible for saving the updated notebook.
     * @return The updated {@link Notebook} with the specified task unmarked.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(this.cmd[1]);

            if (idx > notebook.size() || idx < 1) {
                throw new IndexOutOfBoundException(String.valueOf(idx));
            }

            notebook = notebook.unmark(idx);
            storage.save(notebook);

            String message = String.format("Alright! I've unmarked this task as done:\n\t%s",
                    notebook.getTask(idx));

            ui.showMessage(message);
        } catch (IndexOutOfBoundException
                 | IOException e) {
            ui.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showMessage("The input is not a valid integer.");
        }
        return notebook;
    }
}
