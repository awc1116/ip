package doopies.userinterface;

/**
 * Handles user interactions in the {@code Doopies} application.
 * <p>
 * The {@code Ui} class is responsible for:
 * <ul>
 *     <li>Displaying messages to the user.</li>
 *     <li>Managing welcome and goodbye messages.</li>
 *     <li>Tracking the last displayed message.</li>
 * </ul>
 * </p>
 */
public class Ui {
    private String message;
    private static final String INTRO = "Hello! I'm Doopies\nWhat can I do for you?";
    private static final String END = "Bye. Hope to see you soon!";

    /**
     * Displays the welcome message to the user.
     * <p>
     * This method sets the last message to the predefined introduction message.
     * </p>
     */
    public void showWelcome() {
        this.message = INTRO;
    }

    /**
     * Displays the goodbye message to the user.
     * <p>
     * This method sets the last message to the predefined exit message.
     * </p>
     */
    public void showEnding() {
        this.message = END;
    }

    /**
     * Displays a message to the user.
     * <p>
     * The message is stored internally and can be retrieved using {@link #getLastMessage()}.
     * </p>
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the last displayed message.
     *
     * @return The most recent message stored in the UI.
     */
    public String getLastMessage() {
        return this.message;
    }
}
