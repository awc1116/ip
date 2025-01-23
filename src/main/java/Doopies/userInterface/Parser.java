package Doopies.userInterface;

import Doopies.command.*;

public class Parser {
    public static Command parse(String command) {
        String[] line = command.split(" /");
        String[] cmd = line[0].split(" ");

        return switch (cmd[0].toLowerCase()) {
            case "bye" -> new EndCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(cmd);
            case "unmark" -> new UnmarkCommand(cmd);
            case "delete" -> new DeleteCommand(cmd);
            case "todo" -> new ToDoCommand(cmd);
            case "deadline" -> new DeadlineCommand(line);
            case "event" -> new EventCommand(line);
            default -> new UnknownCommand();
        };
    }
}
