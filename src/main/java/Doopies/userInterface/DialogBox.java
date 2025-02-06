package doopies.userinterface;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box used in the {@code Doopies} application's user interface.
 * <p>
 * A {@code DialogBox} consists of:
 * <ul>
 *     <li>A text label displaying the dialog message.</li>
 *     <li>An {@link ImageView} showing the speaker's profile picture.</li>
 * </ul>
 * It supports user and application responses, with application messages being flipped for differentiation.
 * </p>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml")
            );
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(temp);
        getChildren().setAll(temp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box representing the user's message.
     *
     * @param text The text message from the user.
     * @param img  The user's profile picture.
     * @return A {@code DialogBox} containing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box representing the application's response.
     * <p>
     * The returned dialog box is flipped to visually differentiate system responses.
     * </p>
     *
     * @param text The response text from the application.
     * @param img  The application's profile picture.
     * @return A flipped {@code DialogBox} containing the application's response.
     */
    public static DialogBox getDoopiesDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }
}
