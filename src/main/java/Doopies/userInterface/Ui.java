package doopies.userinterface;

/**
 * Handles user interactions in the doopies.userinterface.Doopies application.
 * <p>
 *     The {@code Ui} class provides methods to display messages, read user input,
 *     and manage the lifecycle of the user interface.
 * </p>
 */
public class Ui {
    private String message;
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

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {

        this.message = INTRO;
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showEnding() {

        this.message = END;
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

        this.message = LINE + "\n" + message + "\n" + LINE;
    }

    public String getLastMessage() {
        return this.message;
    }
}
