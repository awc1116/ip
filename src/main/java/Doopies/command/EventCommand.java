package doopies.command;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Event;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to add an event task to the {@link Notebook}.
 * <p>
 * This command:
 * <ul>
 *     <li>Extracts the task description, start time, and end time from user input.</li>
 *     <li>Creates a new {@link Event} task.</li>
 *     <li>Adds the event task to the {@link Notebook}.</li>
 *     <li>Saves the updated notebook to {@link Storage}.</li>
 *     <li>Displays a confirmation message upon successful addition.</li>
 * </ul>
 * If the input format is incorrect or the description is empty, an error message is displayed.
 * </p>
 */
public class EventCommand extends Command {
    private final String[] line;

    /**
     * Constructs an {@code EventCommand} with the specified parsed input.
     *
     * @param line The parsed input line containing the task description, start time, and end time.
     */
    public EventCommand(String[] line) {
        super();
        this.line = line;
    }

    /**
     * Executes the command to add an event task to the notebook.
     * <p>
     * This method:
     * <ul>
     *     <li>Extracts the task description, start time, and end time from user input.</li>
     *     <li>Validates that the description is not empty.</li>
     *     <li>Creates a new {@link Event} task and adds it to the {@link Notebook}.</li>
     *     <li>Saves the updated notebook to the {@link Storage} system.</li>
     *     <li>Displays a confirmation message to the user.</li>
     * </ul>
     * If the input format is incorrect (e.g., missing start or end time), an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} component used to interact with the user.
     * @param storage  The {@link Storage} system responsible for saving the updated notebook.
     * @return The updated {@link Notebook} containing the new event task.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.line[0].split(" "));
            String from = translate(this.line[1].split(" "));
            String to = translate(this.line[2].split(" "));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("event");
            }

            Event event = new Event(description, from, to);
            notebook = notebook.add(event);
            saveNotebook(notebook, storage, ui);

            String message = String.format("""
                            Got it. I've added this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    event, notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Incorrect format for event.");
        }
        return notebook;
    }
}
