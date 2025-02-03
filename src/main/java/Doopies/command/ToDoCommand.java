package doopies.command;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a command to add a todo task to the notebook.
 * <p>
 * The command parses the task description, creates a new {@link ToDo} task,
 * adds it to the notebook, and saves the updated notebook to storage.
 * </p>
 */
public class ToDoCommand extends Command {
    private final String[] cmd;

    /**
     * Constructs a ToDoCommand with the specified parsed input.
     *
     * @param cmd The parsed input command containing the todo action and task description.
     */
    public ToDoCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    private String translate(String[] cmd) {

        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation adds a {@link ToDo} task to the notebook using the task description provided by the user.
     *     If successful, the updated notebook is saved to storage, and a confirmation message is displayed.
     *     If the description is missing or invalid, an appropriate error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to interact with the user.
     * @param storage  The storage system used to persist the updated notebook.
     * @return The updated notebook containing the new todo task.
     * @throws IOException If an error occurs while saving the notebook to storage.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.cmd);

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of a todo cannot be empty.");
            }

            ToDo todo = new ToDo(description);
            notebook = notebook.add(todo);
            storage.save(notebook);

            String message = String.format("""
                            Got it. I've added this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    todo, notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException
                 | IOException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("OOPS!!! The description of a todo cannot be empty.");
        }
        return notebook;
    }
}
