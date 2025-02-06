package doopies.userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(
                this.dialogContainer.heightProperty());
    }

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
