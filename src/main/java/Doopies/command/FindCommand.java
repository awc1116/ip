package doopies.command;

import java.util.List;

import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to find tasks in the {@link Notebook} that match a given keyword.
 * <p>
 * This command:
 * <ul>
 *     <li>Extracts the search keyword from the user input.</li>
 *     <li>Searches for tasks in the {@link Notebook} whose descriptions contain the keyword.</li>
 *     <li>Displays the matching tasks to the user in a formatted list.</li>
 *     <li>Leaves the {@link Notebook} and {@link Storage} system unchanged.</li>
 * </ul>
 * </p>
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified parsed input.
     *
     * @param cmd The parsed input command containing the find action and the keyword to search for.
     */
    public FindCommand(String[] cmd) {
        super();
        this.keyword = translate(cmd);
    }

    /**
     * Executes the command to search for tasks in the notebook that match the given keyword.
     * <p>
     * This method:
     * <ul>
     *     <li>Retrieves a list of tasks from the {@link Notebook} that contain the specified keyword.</li>
     *     <li>Formats and displays the matching tasks to the user.</li>
     *     <li>Does not modify the {@link Notebook} or {@link Storage} system.</li>
     * </ul>
     * If no matching tasks are found, an appropriate message is displayed.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks.
     * @param ui       The {@link Ui} component used to display the matching tasks to the user.
     * @param storage  The {@link Storage} system (not used in this command).
     * @return The unmodified {@link Notebook}.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        List<Task> tasks = notebook.find(this.keyword);
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, tasks.get(i));
            str.append(temp);
        }
        String res = String.format("Here are the matching tasks in your list:\n%s",
                str.toString().stripTrailing());

        ui.showMessage(res);
        return notebook;
    }
}
