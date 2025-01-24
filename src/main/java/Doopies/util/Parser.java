package doopies.util;

import doopies.command.ClearStorageCommand;
import doopies.command.Command;
import doopies.command.DeadlineCommand;
import doopies.command.DeleteCommand;
import doopies.command.EndCommand;
import doopies.command.EventCommand;
import doopies.command.FindCommand;
import doopies.command.ListCommand;
import doopies.command.MarkCommand;
import doopies.command.ToDoCommand;
import doopies.command.UnknownCommand;
import doopies.command.UnmarkCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Parser {
    public static Command parseCommand(String command) {
        String[] line = command.split(" /");
        String[] cmd = line[0].split(" ");

        return switch (cmd[0].toLowerCase()) {
            case "bye" -> new EndCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(cmd);
            case "unmark" -> new UnmarkCommand(cmd);
            case "delete" -> new DeleteCommand(cmd);
            case "clear" -> new ClearStorageCommand();
            case "find" -> new FindCommand(cmd);
            case "todo" -> new ToDoCommand(cmd);
            case "deadline" -> new DeadlineCommand(line);
            case "event" -> new EventCommand(line);
            default -> new UnknownCommand();
        };
    }

    public static Optional<LocalDateTime> parseMyDate(String dateStr) {
        for (DateFormat format : DateFormat.values()) {
            try {
                return Optional.of(LocalDateTime.parse(dateStr, format.getFormatter()));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return Optional.empty();
    }
}
