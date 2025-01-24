package doopies.userinterface;

import java.util.Scanner;

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

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(INTRO);
    }

    public void showEnding() {
        System.out.println(END);
    }

    public void showMessage(String message) {
        System.out.println(LINE + "\n" + message + "\n" + LINE);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void closeUi() {
        this.sc.close();
    }
}
