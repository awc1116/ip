package doopies.command;

import doopies.exception.UnknownCommandException;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command for handling unrecognized or invalid user input.
 * <p>
 * This command:
 * <ul>
 *     <li>Detects and handles unknown commands entered by the user.</li>
 *     <li>Throws and catches an {@link UnknownCommandException}.</li>
 *     <li>Displays an error message to inform the user that the input is unrecognized.</li>
 *     <li>Leaves the {@link Notebook} and {@link Storage} system unchanged.</li>
 * </ul>
 * </p>
 */
public class UnknownCommand extends Command {

    /**
     * Constructs an {@code UnknownCommand}.
     */
    public UnknownCommand() {

        super();
    }

    /**
     * Executes the command for handling unrecognized user input.
     * <p>
     * This method:
     * <ul>
     *     <li>Throws and catches an {@link UnknownCommandException}.</li>
     *     <li>Displays an error message informing the user that the command is invalid.</li>
     *     <li>Does not modify the {@link Notebook} or {@link Storage} system.</li>
     * </ul>
     * </p>
     *
     * @param notebook The current in-memory {@link Notebook} containing the list of tasks (remains unchanged).
     * @param ui       The {@link Ui} component used to display the error message to the user.
     * @param storage  The {@link Storage} system (not used in this command).
     * @return The unmodified {@link Notebook}.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            throw new UnknownCommandException();
        } catch (UnknownCommandException e) {
            ui.showMessage(e.getMessage());
        }
        return notebook;
    }
}
