package doopies.command;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Deadline;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to add a deadline task to the {@link Notebook}.
 * <p>
 * This command:
 * <ul>
 *     <li>Extracts the task description and due date from user input.</li>
 *     <li>Creates a new {@link Deadline} task.</li>
 *     <li>Adds the deadline task to the {@link Notebook}.</li>
 *     <li>Saves the updated notebook to {@link Storage}.</li>
 * </ul>
 * If the input format is incorrect or the description is empty, an error message is displayed.
 * </p>
 */
public class DeadlineCommand extends Command {
    private final String[] line;

    /**
     * Constructs a {@code DeadlineCommand} with the specified parsed input.
     *
     * @param line The parsed input line containing the task description and due date.
     */
    public DeadlineCommand(String[] line) {
        super();
        this.line = line;
    }

    /**
     * Executes the command to add a deadline task to the notebook.
     * <p>
     * This method:
     * <ul>
     *     <li>Extracts the task description and due date from user input.</li>
     *     <li>Checks if the description is empty and throws an exception if so.</li>
     *     <li>Creates a new {@link Deadline} task and adds it to the notebook.</li>
     *     <li>Saves the updated notebook to the {@link Storage} system.</li>
     *     <li>Displays a confirmation message to the user.</li>
     * </ul>
     * If the input is incorrect (e.g., missing a `/by` separator), an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} used to interact with the user.
     * @param storage  The {@link Storage} system used to persist or retrieve tasks.
     * @return The updated {@link Notebook} containing the new deadline task.
     * @throws ArrayIndexOutOfBoundsException If the input format is incorrect (e.g., missing `/by`).
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.line[0].split(" "));
            String dueDate = translate(this.line[1].split(" "));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("deadline");
            }

            Deadline deadline = new Deadline(description, dueDate);
            notebook = notebook.add(deadline);
            saveNotebook(notebook, storage, ui);

            String message = String.format("""
                            Got it. I've added this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    deadline,
                    notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Incorrect format for deadline.");
        }
        return notebook;
    }
}
