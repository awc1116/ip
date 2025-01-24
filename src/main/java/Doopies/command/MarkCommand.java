package doopies.command;

import doopies.exception.IndexOutOfBoundException;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private final String[] cmd;

    public MarkCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(this.cmd[1]);

            if (idx > notebook.size() || idx < 1) {
                throw new IndexOutOfBoundException(idx + " is not in your list.");
            }

            notebook = notebook.mark(idx);
            storage.save(notebook);

            String message = String.format("Alright! I've marked this task as done:\n\t%s",
                    notebook.getTask(idx));

            ui.showMessage(message);
        } catch (IndexOutOfBoundException
                 | IOException e) {
            ui.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showMessage("The input is not a valid integer.");
        }
        return notebook;
    }
}
