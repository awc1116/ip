package doopies.command;

import doopies.exception.IndexOutOfBoundException;
import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.userinterface.Ui;

public class DeleteCommand extends Command {
    private final String[] cmd;

    public DeleteCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui) {
        try {
            int idx = Integer.parseInt(this.cmd[1]);

            if (idx > notebook.size() || idx < 1) {
                throw new IndexOutOfBoundException(idx + " is not in your list.");
            }

            Task task = notebook.getTask(idx);
            notebook = notebook.delete(idx);

            String message = String.format("""
                            Noted. I've removed this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    task, notebook.size());

            ui.showMessage(message);
        } catch (IndexOutOfBoundException e) {
            ui.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showMessage("The input is not a valid integer.");
        }
        return notebook;
    }
}
