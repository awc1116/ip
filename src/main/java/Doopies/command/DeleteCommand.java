package doopies.command;

import doopies.exception.IndexOutOfBoundException;
import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the notebook.
 * <p>
 *     The command removes a task at a specified index from the notebook, saves the updated notebook to storage,
 *     and displays a confirmation message to the user. If the index is invalid or out of bounds, an error message is displayed.
 * </p>
 */
public class DeleteCommand extends Command {
    /** The parsed input command containing the delete action and the task index. */
    private final String[] cmd;

    /**
     * Constructs a DeleteCommand with the specified parsed input.
     *
     * @param cmd The parsed input command containing the delete action and the task index.
     */
    public DeleteCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation deletes a task from the notebook at the specified index. If successful, the updated
     *     notebook is saved to storage, and a confirmation message is displayed. If the index is invalid or not an
     *     integer, an appropriate error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to interact with the user.
     * @param storage  The storage system used to persist the updated notebook.
     * @return The updated notebook after removing the task.
     * @throws IOException If an error occurs while saving the notebook to storage.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(this.cmd[1]);

            if (idx > notebook.size() || idx < 1) {
                throw new IndexOutOfBoundException(idx + " is not in your list.");
            }

            Task task = notebook.getTask(idx);
            notebook = notebook.delete(idx);
            storage.save(notebook);

            String message = String.format("""
                            Noted. I've removed this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    task, notebook.size());

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
