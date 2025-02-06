package doopies.command;

import java.io.IOException;
import java.util.Arrays;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to add a to-do task to the {@link Notebook}.
 * <p>
 * This command:
 * <ul>
 *     <li>Extracts the task description from user input.</li>
 *     <li>Creates a new {@link ToDo} task.</li>
 *     <li>Adds the task to the {@link Notebook}.</li>
 *     <li>Saves the updated notebook to {@link Storage}.</li>
 *     <li>Displays a confirmation message upon successful addition.</li>
 * </ul>
 * If the description is missing or invalid, an error message is displayed.
 * </p>
 */
public class ToDoCommand extends Command {
    private final String[] cmd;

    /**
     * Constructs a {@code ToDoCommand} with the specified parsed input.
     *
     * @param cmd The parsed input command containing the to-do action and task description.
     */
    public ToDoCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    private String translate(String[] cmd) {
        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }

    /**
     * Executes the command to add a to-do task to the notebook.
     * <p>
     * This method:
     * <ul>
     *     <li>Extracts the task description from user input.</li>
     *     <li>Validates that the description is not empty.</li>
     *     <li>Creates a new {@link ToDo} task and adds it to the {@link Notebook}.</li>
     *     <li>Saves the updated notebook to the {@link Storage} system.</li>
     *     <li>Displays a confirmation message to the user.</li>
     * </ul>
     * If the description is missing or invalid, an appropriate error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} component used to interact with the user.
     * @param storage  The {@link Storage} system responsible for saving the updated notebook.
     * @return The updated {@link Notebook} containing the new to-do task.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.cmd);

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("todo");
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
