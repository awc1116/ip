package doopies.userinterface;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the {@code Doopies} application.
 * <p>
 * This class initializes the graphical user interface (GUI) using JavaFX and
 * loads the {@code MainWindow} layout. It also creates an instance of {@link Doopies}
 * to handle user interactions.
 * </p>
 */
public class Main extends Application {
    private final Doopies doopies = new Doopies();

    /**
     * Starts the JavaFX application.
     * <p>
     * This method:
     * <ul>
     *     <li>Loads the {@code MainWindow} FXML layout.</li>
     *     <li>Initializes the scene and sets it on the primary stage.</li>
     *     <li>Links the {@code Doopies} instance to the {@code MainWindow} controller.</li>
     * </ul>
     * </p>
     *
     * @param stage The primary JavaFX stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml")
            );
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            controller.setDoopies(this.doopies);
            controller.showWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the JavaFX application.
     * <p>
     * This method is automatically called when the program starts, and it invokes
     * {@code Application.launch()} to initialize the JavaFX runtime.
     * </p>
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
