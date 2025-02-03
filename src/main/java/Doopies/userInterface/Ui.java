package doopies.userinterface;

import java.util.Scanner;

/**
 * Handles user interactions in the doopies.Doopies application.
 * <p>
 *     The {@code Ui} class provides methods to display messages, read user input,
 *     and manage the lifecycle of the user interface.
 * </p>
 */
public class Ui {
    private static final String LINE = "_".repeat(60);
    private static final String INTRO = String.format("""
                %s
                Hello! I'm Doopies
                What can I do for you?
                %s
                """,
            LINE,
            LINE);
    private static final String END = String.format("""
                %s
                Bye. Hope to see you soon!
                %s
                """,
            LINE,
            LINE);
    private final Scanner sc;

    /**
     * Constructs a new {@code Ui} instance and initializes the input scanner.
     */
    public Ui() {

        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {

        System.out.println(INTRO);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showEnding() {

        System.out.println(END);
    }

    /**
     * Displays a formatted message to the user.
     * <p>
     *     The message is enclosed within two horizontal lines for readability.
     * </p>
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {

        System.out.println(LINE + "\n" + message + "\n" + LINE);
    }

    /**
     * Reads a command entered by the user.
     * <p>
     *     This method waits for input from the console and returns the entered command as a string.
     * </p>
     *
     * @return The command entered by the user.
     */
    public String readCommand() {

        return this.sc.nextLine();
    }

    /**
     * Closes the user interface.
     * <p>
     *     This method closes the scanner and releases any associated system resources.
     *     It should be called when the application is shutting down.
     * </p>
     */
    public void closeUi() {

        this.sc.close();
    }
}
