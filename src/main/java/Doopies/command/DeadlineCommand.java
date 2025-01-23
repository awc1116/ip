package Doopies.command;

import Doopies.Exception.EmptyDescriptionException;
import Doopies.notebook.Notebook;
import Doopies.notebook.Deadline;
import Doopies.userInterface.Ui;

import java.util.Arrays;

public class DeadlineCommand extends Command {
    private final String[] line;

    public DeadlineCommand(String[] line) {
        super();
        this.line = line;
    }

    private String translate(String[] cmd) {
        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui) {
        try {
            String description = translate(this.line[0].split(" "));
            String dueDate = translate(this.line[1].split(" "));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of a deadline cannot be empty.");
            }

            Deadline deadline = new Deadline(description, dueDate);
            notebook = notebook.add(deadline);

            String message = String.format("Got it. I've added this task:\n\t%s\n" +
                            "Now you have %d tasks in the list.",
                    deadline.toString(), notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Incorrect format for deadline.");
        }
        return notebook;
    }
}
