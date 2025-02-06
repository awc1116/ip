package doopies.userinterface;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML
    private Label titleLabel;
    @FXML
    private ImageView logoImage;
    @FXML
    private HBox titleBar;

    private Doopies doopies;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.jpg"));

    private final Image doopiesImage = new Image(this.getClass().getResourceAsStream("/images/doopies_logo.jpg"));

    /**
     * Initializes the main window.
     * <p>
     * Binds the scroll pane's vertical property to the height of the dialog container,
     * ensuring that new messages are always visible.
     * </p>
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.scrollPane.setFitToHeight(false);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * -1; // Reverse direction if needed
            this.scrollPane.setVvalue(this.scrollPane.getVvalue() + deltaY / this.scrollPane.getHeight());
            event.consume();
        });
        this.scrollPane.setPannable(true);

        this.logoImage.setImage(this.doopiesImage);
        this.logoImage.setFitHeight(70);
        this.logoImage.setFitWidth(70);
        Circle clip = new Circle(35, 25, 20);
        this.logoImage.setClip(clip);
    }

    public void showWelcomeMessage() {
        if (this.doopies != null) {
            String welcomeMessage = this.doopies.getResponse("start");
            this.dialogContainer.getChildren().add(
                    DialogBox.getDoopiesDialog(welcomeMessage, this.doopiesImage)
            );
        }
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

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) this.dialogContainer.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}
