package doopies;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import doopies.userinterface.Doopies;

class DoopiesTest {
    private final String testFilePath = "./data/test_doopies.txt";
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setIn(new ByteArrayInputStream("".getBytes()));

        Path path = Paths.get(testFilePath);
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void simulateInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private void assertOutputContains(String expectedOutput) {
        assertTrue(outputStream.toString().contains(expectedOutput),
                "Expected output to contain:\n" + expectedOutput
                        + "\nBut current output is:\n" + outputStream);
    }

    @Test
    void testTodoCommandValid() {
        simulateInput("todo read book\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Got it. I've added this task:\n\t[T][ ] read book");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testTodoCommandEmptyDescription() {
        simulateInput("todo\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("OOPS!!! The description of a todo cannot be empty.");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testMarkCommandValid() {
        simulateInput("list\ntodo read book\nmark 1\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Alright! I've marked this task as done:\n\t[T][X] read book");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testListCommand() {
        simulateInput("todo read book\nlist\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Here are the tasks in your list:\n1. [T][ ] read book");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testMultipleTasksMarkCommand() {
        simulateInput("todo read book\ntodo join sports club\nmark 2\nlist\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Alright! I've marked this task as done:\n\t[T][X] join sports club");
        assertOutputContains("1. [T][ ] read book\n2. [T][X] join sports club");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testUnmarkCommand() {
        simulateInput("todo read book\nmark 1\nunmark 1\nlist\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Alright! I've unmarked this task as done:\n\t[T][ ] read book");
        assertOutputContains("Here are the tasks in your list:\n1. [T][ ] read book");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testDeadlineCommandValid() {
        simulateInput("deadline return book /by 31/1/2025 2359\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Got it. I've added this task:\n\t[D][ ] return book (by: Jan 31 2025, 11:59 pm)");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testDeadlineCommandEmptyDescription() {
        simulateInput("deadline /by 31/1/2025 2359\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("OOPS!!! The description of a deadline cannot be empty.");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testDeadlineCommandIncorrectFormat() {
        simulateInput("deadline return book\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Incorrect format for deadline.");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testEventCommandValid() {
        simulateInput("event meeting /from 24/1/2025 1400 /to 24/1/2025 1600\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Got it. I've added this task:\n\t[E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testEventCommandEmptyDescription() {
        simulateInput("event /from 24/1/2025 1400 /to 24/1/2025 1600\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("OOPS!!! The description of an event cannot be empty.");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testEventCommandIncorrectFormat() {
        simulateInput("event meeting\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("Incorrect format for event.");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void testUnknownCommand() {
        simulateInput("blah\nbye\n");
        new Doopies(testFilePath).run();
        assertOutputContains("OOPS!!! I'm sorry, but I don't know what that means :-(");
        assertOutputContains("Bye. Hope to see you soon!");
    }

    @Test
    void loadingStorage() {
        simulateInput("""
            todo read book
            deadline return book /by 31/1/2025 2359
            event meeting /from 24/1/2025 1400 /to 24/1/2025 1600
            bye
            """);
        new Doopies(testFilePath).run();

        simulateInput("list\nbye\n");
        new Doopies(testFilePath).run();

        assertOutputContains("""
            Here are the tasks in your list:
            1. [T][ ] read book
            2. [E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)
            3. [D][ ] return book (by: Jan 31 2025, 11:59 pm)
            """);
    }

    @Test
    void testFindCommandIntegration() {
        String input = """
                todo read book
                todo write book
                todo play football
                find book
                bye
                """;

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Doopies doopies = new Doopies("./data/test_storage.txt");
        doopies.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Here are the matching tasks in your list:"));
        assertTrue(output.contains("[T][ ] read book"));
        assertTrue(output.contains("[T][ ] write book"));
    }
}