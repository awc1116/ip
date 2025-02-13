package doopies.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import doopies.command.ClearStorageCommand;
import doopies.command.Command;
import doopies.command.DeadlineCommand;
import doopies.command.DeleteCommand;
import doopies.command.EndCommand;
import doopies.command.EventCommand;
import doopies.command.FindCommand;
import doopies.command.ListCommand;
import doopies.command.MarkCommand;
import doopies.command.RemindersCommand;
import doopies.command.StartCommand;
import doopies.command.ToDoCommand;
import doopies.command.UnknownCommand;
import doopies.command.UnmarkCommand;

/**
 * Handles the parsing of user input into commands and dates.
 * <p>
 *     The {@code Parser} class provides methods to:
 * <ul>
 *     <li>Convert user input into {@link Command} objects that the application can execute.</li>
 *     <li>Parse date strings into {@link LocalDateTime} objects using predefined formats in {@link DateFormat}.</li>
 * </ul>
 * </p>
 */
public class Parser {

    /**
     * Parses the user input into a {@link Command} object.
     * <p>
     *     This method analyzes the input string, determines the command type,
     *     and constructs the appropriate {@link Command} object. If the command is unrecognized,
     *     an {@link UnknownCommand} is returned.
     * </p>
     *
     * @param command The user input string to parse.
     * @return A {@link Command} object representing the parsed command.
     */
    public static Command parseCommand(String command) {
        assert command != null && !command.isBlank() : "Command cannot be null or empty";
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
        case "start" -> new StartCommand();
        case "reminders" -> new RemindersCommand();
        default -> new UnknownCommand();
        };
    }

    /**
     * Parses a date string into a {@link LocalDateTime} object.
     * <p>
     *     This method attempts to parse the input string using all date formats defined in {@link DateFormat}.
     *     If the string cannot be parsed using any of the formats, an empty {@link Optional} is returned.
     * </p>
     *
     * @param dateStr The date string to parse.
     * @return An {@link Optional} containing the parsed {@link LocalDateTime} if successful,
     *     or an empty {@link Optional} if the string cannot be parsed.
     */
    public static Optional<LocalDateTime> parseMyDate(String dateStr) {
        assert dateStr != null && !dateStr.isBlank() : "Date string cannot be null or empty";
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
