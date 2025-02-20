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
    private static final String INTRO = "Hello! I'm Doopies\nWhat can I do for you?";
    private static final String END = "Bye. Hope to see you soon!";
    private static final String HELP = """
            Command List
            ___________________________________________________________________________________________________________

            Notes:
            1) Commands within {} are for user to fill up and {} can be ignored when typing in actual command.

            2) When marking, unmarking or deleting tasks please call list to confirm the task number.

            3) Current list will be stored in ../data/doopies.txt in chronological order
            (todo tasks will be prioritise).
            ___________________________________________________________________________________________________________
            
            Add Tasks:
            1) todo task (no due date or no duration):
               command: todo {description}
               example: todo read book

            2) deadline task (task with a due date):
                command: deadline /by {DD/MM/YYYY HHmm}
               example: return book /by 20/02/2025 2359
            
            3) event task (task with a period to do it):
                command: event /from {DD/MM/YYYY HHmm} /to {DD/MM/YYYY HHmm}
                example: event project meeting /from 21/2/2025 1400 /to /21/2/2025 1600
            ___________________________________________________________________________________________________________
            
            Viewing Tasks:
            1) view all task in task list:
                command: list

            2) view all undone todo task and deadline due within 24hrs or event starting within 24hrs:
                command: reminders
            ___________________________________________________________________________________________________________
            
            Managing Tasks:
            1) mark task as done:
                command: mark {task number on list}
            
            2) unmark task as done:
                command: unmark {task number on list}
            
            3) delete task:
                command: delete {task number on list}
            ___________________________________________________________________________________________________________
            
            Miscellaneous:
            1) clear storage saved in hard disk:
                command: clear
            
            2) finding tasks using keyword(s) in task:
                command: find {keyword(s)}
            
            3) shutdown the bot (app will close a second after this command):
                command: byes
            ___________________________________________________________________________________________________________
            """;
    private String message;

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
     * Displays the help message to the user.
     * <p>
     * This method sets the last message to the predefined help message.
     * </p>
     */
    public void showHelp() {
        this.message = HELP;
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
