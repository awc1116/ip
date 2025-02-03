package doopies.command;

import java.io.IOException;

import doopies.exception.IndexOutOfBoundException;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to unmark a task as not done in the notebook.
 * <p>
 *     The {@code UnmarkCommand} removes the completion status of a specified task in the notebook,
 *     saves the updated notebook to storage, and notifies the user.
 * </p>
 */
public class UnmarkCommand extends Command {
    private final String[] cmd;

    /**
     * Constructs an {@code UnmarkCommand} with the specified command arguments.
     *
     * @param cmd The parsed command arguments containing the unmark action and the task index.
     */
    public UnmarkCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation unmarks the specified task in the notebook, saves the updated notebook to storage,
     *     and displays a confirmation message to the user. If the index is invalid or storage cannot be saved,
     *     an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to display messages to the user.
     * @param storage  The storage system used to persist changes to the notebook.
     * @return The updated notebook with the specified task unmarked.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(this.cmd[1]);

            if (idx > notebook.size() || idx < 1) {
                throw new IndexOutOfBoundException(idx + " is not in your list.");
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
