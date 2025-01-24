package doopies.command;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Notebook;
import doopies.notebook.ToDo;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;
import java.util.Arrays;

public class ToDoCommand extends Command {
    private final String[] cmd;

    public ToDoCommand(String[] cmd) {
        super();
        this.cmd = cmd;
    }

    private String translate(String[] cmd) {
        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.cmd);

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of a todo cannot be empty.");
            }

            ToDo todo = new ToDo(description);
            notebook = notebook.add(todo);
            storage.save(notebook);

            String message = String.format("""
                            Got it. I've added this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    todo, notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException
                 | IOException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("OOPS!!! The description of a todo cannot be empty.");
        }
        return notebook;
    }
}
