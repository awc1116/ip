package doopies.command;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Deadline;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a command to add a deadline task to the notebook.
 * <p>
 *     The command parses the task description and due date, creates a new {@link Deadline} task, adds it to the notebook,
 *     and saves the updated notebook storage.
 * </p>
 */
public class DeadlineCommand extends Command {
    private final String[] line;

    /**
     * Constructs a DeadlineCommand with the specified parsed input.
     *
     * @param line The parsed input line containing the task description and due date.
     */
    public DeadlineCommand(String[] line) {
        super();
        this.line = line;
    }

    private String translate(String[] cmd) {

        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }

    /**
     * <p>
     *     This implementation adds a {@link Deadline} task to the notebook using the task description
     *     and due date provided by the user. If successful, the updated notebook is saved to storage,
     *     and a confirmation message is displayed to the user. If the input is incomplete or invalid,
     *     an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to interact with the user.
     * @param storage  The storage system used to persist or retrieve tasks.
     * @return The updated notebook containing the new deadline task.
     * @throws ArrayIndexOutOfBoundsException If the input format is incorrect (e.g., missing `/by`).
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.line[0].split(" "));
            String dueDate = translate(this.line[1].split(" "));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of a deadline cannot be empty.");
            }

            Deadline deadline = new Deadline(description, dueDate);
            notebook = notebook.add(deadline);
            storage.save(notebook);

            String message = String.format("""
                            Got it. I've added this task:
                            \t%s
                            Now you have %d tasks in the list.""", deadline, notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException
                 | IOException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Incorrect format for deadline.");
        }
        return notebook;
    }
}
