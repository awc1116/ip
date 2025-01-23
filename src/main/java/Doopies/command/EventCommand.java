package Doopies.command;

import Doopies.Exception.EmptyDescriptionException;
import Doopies.notebook.Notebook;
import Doopies.notebook.Event;
import Doopies.userInterface.Ui;

import java.util.Arrays;

public class EventCommand extends Command {
    private final String[] line;

    public EventCommand(String[] line) {
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
            String from = translate(this.line[1].split(" "));
            String to = translate(this.line[2].split(" "));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! " +
                        "The description of an event cannot be empty.");
            }

            Event event = new Event(description, from, to);
            notebook = notebook.add(event);

            String message = String.format("Got it. I've added this task:\n\t%s\n" +
                            "Now you have %d tasks in the list.",
                    event.toString(), notebook.size());

            ui.showMessage(message);
        } catch (EmptyDescriptionException e) {
            ui.showMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Incorrect format for event.");
        }
        return notebook;
    }
}
