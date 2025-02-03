package doopies.command;

import java.util.Arrays;
import java.util.List;

import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to find tasks in the notebook that match a given keyword.
 * <p>
 *     The {@code FindCommand} searches for tasks whose descriptions contain the specified keyword,
 *     displays the results to the user, and leaves the notebook unchanged.
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

    private String translate(String[] cmd) {

        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation searches for tasks in the notebook whose descriptions contain the specified keyword.
     *     The matching tasks are displayed to the user in a formatted list. The notebook and storage are not modified.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks.
     * @param ui       The user interface used to display the matching tasks to the user.
     * @param storage  The storage system (not used in this command).
     * @return The unmodified notebook.
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
