package doopies.command;

import doopies.exception.IndexOutOfBoundException;
import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final String[] cmd;

    public DeleteCommand(String[] cmd) {
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

            Task task = notebook.getTask(idx);
            notebook = notebook.delete(idx);
            storage.save(notebook);

            String message = String.format("""
                            Noted. I've removed this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    task, notebook.size());

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
