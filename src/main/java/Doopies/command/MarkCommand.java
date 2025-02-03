package doopies.command;

import doopies.exception.IndexOutOfBoundException;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task in the notebook as completed.
 * <p>
 *     This command updates the status of a specific task in the notebook to "done",
 *     saves the updated notebook to storage, and displays a confirmation message to the user.
 * </p>
 */
public class MarkCommand extends Command {
    private final String[] cmd;

    /**
     * Constructs a MarkCommand with the specified parsed input.
     *
     * @param cmd The parsed input command containing the mark action and the task index.
     */
    public MarkCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation marks the specified task in the notebook as done.
     *     If the index is valid, the updated notebook is saved to storage, and a confirmation message is displayed.
     *     If the index is invalid or not an integer, an appropriate error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to interact with the user.
     * @param storage  The storage system used to persist the updated notebook.
     * @return The updated notebook with the specified task marked as done.
     * @throws IOException If an error occurs while saving the notebook to storage.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(this.cmd[1]);

            if (idx > notebook.size() || idx < 1) {
                throw new IndexOutOfBoundException(idx + " is not in your list.");
            }

            notebook = notebook.mark(idx);
            storage.save(notebook);

            String message = String.format("Alright! I've marked this task as done:\n\t%s",
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
