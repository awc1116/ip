package Doopies.command;

import Doopies.Exception.EmptyDescriptionException;
import Doopies.notebook.Notebook;
import Doopies.notebook.ToDo;
import Doopies.userInterface.Ui;

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
    public Notebook execute(Notebook notebook, Ui ui) {
        try {
            String description = translate(this.cmd);

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of a todo cannot be empty.");
            }

            ToDo todo = new ToDo(description);
            notebook = notebook.add(todo);

            String message = String.format("Got it. I've added this task:\n\t%s\n" +
                    "Now you have %d tasks in the list.",
                    todo.toString(), notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("OOPS!!! The description of a todo cannot be empty.");
        }
        return notebook;
    }
}
