package doopies.command;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Event;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a command to add an event task to the notebook.
 * <p>
 *     The command parses the task description, start time, and end time, creates a new {@link Event} task,
 *     adds it to the notebook, and saves the updated notebook to storage.
 * </p>
 */
public class EventCommand extends Command {
    private final String[] line;

    /**
     * Constructs an EventCommand with the specified parsed input.
     *
     * @param line The parsed input line containing the task description, start time, and end time.
     */
    public EventCommand(String[] line) {
        super();
        this.line = line;
    }

    private String translate(String[] cmd) {

        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation adds an {@link Event} task to the notebook using the task description,
     *     start time, and end time provided by the user. If successful, the updated notebook is saved to storage,
     *     and a confirmation message is displayed. If the input is incomplete or invalid, an error message is displayed.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to interact with the user.
     * @param storage  The storage system used to persist the updated notebook.
     * @return The updated notebook containing the new event task.
     * @throws IOException If an error occurs while saving the notebook to storage.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.line[0].split(" "));
            String from = translate(this.line[1].split(" "));
            String to = translate(this.line[2].split(" "));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of an event cannot be empty.");
            }

            Event event = new Event(description, from, to);
            notebook = notebook.add(event);
            storage.save(notebook);

            String message = String.format("""
                            Got it. I've added this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    event, notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException
                 | IOException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Incorrect format for event.");
        }
        return notebook;
    }
}
