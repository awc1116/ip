package doopies.util;

import doopies.command.ClearStorageCommand;
import doopies.command.Command;
import doopies.command.DeadlineCommand;
import doopies.command.EndCommand;
import doopies.command.EventCommand;
import doopies.command.ListCommand;
import doopies.command.ToDoCommand;
import doopies.command.UnknownCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

public class ParserTest {

    @Test
    void testParseCommandValid() {
        Command command = Parser.parseCommand("todo read book");
        assertInstanceOf(ToDoCommand.class, command);

        command = Parser.parseCommand("deadline return book /by 31/1/2025 2359");
        assertInstanceOf(DeadlineCommand.class, command);

        command = Parser.parseCommand("event meeting /from 24/1/2025 1400 /to 24/1/2025 1600");
        assertInstanceOf(EventCommand.class, command);

        command = Parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, command);

        command = Parser.parseCommand("bye");
        assertInstanceOf(EndCommand.class, command);

        command = Parser.parseCommand("clear");
        assertInstanceOf(ClearStorageCommand.class, command);
    }

    @Test
    void testParseCommandInvalid() {
        Command command = Parser.parseCommand("blah blah blah");
        assertInstanceOf(UnknownCommand.class, command);
    }

    @Test
    void testParseMyDate() {
        // Test valid dates
        Optional<LocalDateTime> date = Parser.parseMyDate("31/1/2025 2359");
        assertTrue(date.isPresent());
        assertEquals("Jan 31 2025, 11:59 pm", date.get().format(DateFormat.OUTPUT_FORMAT.getFormatter()));

        date = Parser.parseMyDate("24/1/2025 1400");
        assertTrue(date.isPresent());
        assertEquals("Jan 24 2025, 02:00 pm", date.get().format(DateFormat.OUTPUT_FORMAT.getFormatter()));

        // Test invalid date
        date = Parser.parseMyDate("invalid date");
        assertTrue(date.isEmpty());
    }

    @Test
    void testParseIncompleteCommand() {
        Command command = Parser.parseCommand("todo");
        assertInstanceOf(ToDoCommand.class, command);

        command = Parser.parseCommand("deadline");
        assertInstanceOf(DeadlineCommand.class, command);
    }
}

