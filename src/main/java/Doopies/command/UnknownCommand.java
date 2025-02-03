package doopies.command;

import doopies.exception.UnknownCommandException;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

/**
 * Represents a command for handling unrecognized or invalid user input.
 * <p>
 * This command displays an error message to the user indicating that the input is invalid.
 * The notebook and storage remain unchanged.
 * </p>
 */
public class UnknownCommand extends Command {

    /**
     * Constructs an UnknownCommand.
     */
    public UnknownCommand() {

        super();
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This implementation throws and catches an {@link UnknownCommandException}, displaying an error message
     *     to inform the user that the input is unrecognized. The notebook and storage are not modified.
     * </p>
     *
     * @param notebook The current in-memory notebook containing the list of tasks (remains unchanged).
     * @param ui       The user interface used to display the error message to the user.
     * @param storage  The storage system (not used in this command).
     * @return The unmodified notebook.
     */
    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            throw new UnknownCommandException("OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        } catch (UnknownCommandException e) {
            ui.showMessage(e.getMessage());
        }
        return notebook;
    }
}
