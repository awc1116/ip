import java.util.Scanner;
import Doopies.List.*;

public class Doopies {
    private static final String LINE = "_".repeat(60);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyList myList = new MyList();

        String intro = String.format("""
                %s
                Hello! I'm Doopies
                What can I do for you?
                %s
                """,
                LINE,
                LINE);

        String end = String.format("""
                %s
                Bye. Hope to see you soon!
                %s
                """,
                LINE,
                LINE);

        System.out.println(intro);

        while (true) {
            String in = sc.nextLine();
            if (in.equalsIgnoreCase("bye")) {
                System.out.println(end);
                break;
            } else if (in.equalsIgnoreCase("list")) {
                System.out.println(myList);
            }else {
                myList = myList.add(in);
                System.out.printf("""
                                %s
                                added: %s
                                %s
                                %n""",
                        LINE,
                        in,
                        LINE);
            }
        }
        sc.close();
    }
}