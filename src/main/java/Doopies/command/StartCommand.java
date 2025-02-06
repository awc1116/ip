package doopies.command;

import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command to display the welcome message when the application starts.
 * <p>
 * The {@code StartCommand} is executed at the beginning of the application lifecycle
 * to greet the user with an introductory message. This command does not modify
 * the notebook or interact with storage.
 * </p>
 */
public class StartCommand extends Command {

    /**
     * Constructs a new {@code StartCommand}.
     * <p>
     * This command does not require any parameters and serves only to display
     * the welcome message.
     * </p>
     */
    public StartCommand() {
        super();
    }

    /**
     * Executes the start command by displaying the welcome message.
     * <p>
     * This implementation calls {@link Ui#showWelcome()} to display the introductory message.
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
        ui.showWelcome();
        return notebook;
    }
}
