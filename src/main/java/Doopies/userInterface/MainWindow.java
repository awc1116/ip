package doopies.userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main user interface window for the {@code Doopies} application.
 * <p>
 * This class manages user interactions, including:
 * <ul>
 *     <li>Displaying a scrollable dialog container.</li>
 *     <li>Handling user input.</li>
 *     <li>Displaying responses from {@link Doopies}.</li>
 * </ul>
 * </p>
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Doopies doopies;
    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/userImage.jpg"));
    private final Image doopiesImage = new Image(this.getClass()
            .getResourceAsStream("/images/doopies_logo.jpg"));

    /**
     * Initializes the main window.
     * <p>
     * Binds the scroll pane's vertical property to the height of the dialog container,
     * ensuring that new messages are always visible.
     * </p>
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(
                this.dialogContainer.heightProperty());
    }

    /**
     * Sets the {@code Doopies} instance for the UI to interact with.
     *
     * @param doopies The {@link Doopies} instance handling application logic.
     */
    public void setDoopies(Doopies doopies) {
        this.doopies = doopies;
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.doopies.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDoopiesDialog(response, this.doopiesImage)
        );
        this.userInput.clear();
    }
}
