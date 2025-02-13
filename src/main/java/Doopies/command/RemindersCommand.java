package doopies.command;

import java.util.List;

import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to display reminders for tasks that are due within the next 24 hours.
 * <p>
 * This command retrieves tasks from the {@link Notebook} that are due within the next day,
 * including all {@link Task} types: ToDo, Deadline, and Event. If no tasks are due within the time frame,
 * it notifies the user accordingly.
 * </p>
 */
public class RemindersCommand extends Command {

    /**
     * Constructs a new {@code RemindersCommand}.
     * <p>
     * This command requires no additional parameters and serves to display reminders
     * when executed.
     * </p>
     */
    public RemindersCommand() {
        super();
    }

    /**
     * Executes the reminders command by retrieving and displaying tasks due within the next 24 hours.
     * <p>
     * This method:
     * <ul>
     *     <li>Uses {@link Notebook#getTasksDueWithinOneDay()} to fetch tasks due soon.</li>
     *     <li>Formats and displays the tasks using the {@link Ui} component.</li>
     *     <li>If no tasks are due, displays an appropriate message.</li>
     * </ul>
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} component used to interact with the user.
     * @param storage  The {@link Storage} system (not used in this command).
     * @return The unmodified {@link Notebook}.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        List<Task> tasks = notebook.getTasksDueWithinOneDay();
        String res;

        if (tasks.isEmpty()) {
            res = "No upcoming tasks within the next day.";
        } else {
            StringBuilder str = new StringBuilder();

            for (int i = 0; i < tasks.size(); i++) {
                String temp = String.format("%d. %s\n", i + 1, tasks.get(i));
                str.append(temp);
            }
            res = String.format("Reminder: You have the following tasks due within the next 24 hours:\n%s",
                    str.toString().stripTrailing());
        }

        ui.showMessage(res);
        return notebook;
    }
}
