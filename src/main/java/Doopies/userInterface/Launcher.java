package doopies.userinterface;

import javafx.application.Application;

/**
 * The {@code Launcher} class serves as the entry point of the application.
 * <p>
 * This class contains the {@code main} method, which is responsible for
 * launching the JavaFX application by invoking {@code Application.launch}.
 * </p>
 */
public class Launcher {
    /**
     * The main entry point of the application.
     * <p>
     * This method calls {@code Application.launch} to start the JavaFX application.
     * </p>
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
