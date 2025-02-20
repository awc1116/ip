package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to display the help message when the user request for help.
 * <p>
 * The {@code HelpCommand} display the list of command of this bot. This command does not modify
 * the notebook or interact with storage.
 * </p>
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new {@code HelpCommand}.
     * <p>
     * This command does not require any parameters and serves only to display
     * the help message.
     * </p>
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the help command by displaying the help message.
     * <p>
     * This implementation calls {@link Ui#showHelp()} to display the help message.
     * The notebook remains unchanged, and no modifications are made to storage.
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} (remains unchanged).
     * @param ui       The {@link Ui} component used to display the welcome message.
     * @param storage  The {@link Storage} system (not used in this command).
     * @return The unmodified {@link Notebook}.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        ui.showHelp();
        return notebook;
    }
}
