package doopies.command;

import doopies.exception.EmptyDescriptionException;
import doopies.notebook.Event;
import doopies.notebook.Notebook;
import doopies.storage.Storage;
import doopies.userinterface.Ui;

import java.io.IOException;
import java.util.Arrays;

public class EventCommand extends Command {
    private final String[] line;

    public EventCommand(String[] line) {
        super();
        this.line = line;
    }

    @Override
    public Notebook execute(Notebook notebook, Ui ui, Storage storage) {
        try {
            String description = translate(this.line[0].split(" "));
            String from = translate(this.line[1].split(" "));
            String to = translate(this.line[2].split(" "));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of an event cannot be empty.");
            }

            Event event = new Event(description, from, to);
            notebook = notebook.add(event);
            storage.save(notebook);

            String message = String.format("""
                            Got it. I've added this task:
                            \t%s
                            Now you have %d tasks in the list.""",
                    event, notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException
                 | IOException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Incorrect format for event.");
        }
        return notebook;
    }

    private String translate(String[] cmd) {
        return String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
    }
}
