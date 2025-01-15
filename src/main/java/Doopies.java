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
            String[] line = sc.nextLine().split(" ");
            if (line[0].equalsIgnoreCase("bye")) {
                System.out.println(end);
                break;
            } else if (line[0].equalsIgnoreCase("list")) {
                System.out.println(myList);
            } else if (line[0].equalsIgnoreCase("mark")) {
                int idx = Integer.parseInt(line[1]);
                myList = myList.mark(idx);
                String res = String.format("""
                        %s
                        Alright! I've marked this task as done:
                        \t%s
                        %s
                        """,
                        LINE,
                        myList.getTask(idx),
                        LINE);
                System.out.println(res);
            } else if (line[0].equalsIgnoreCase("unmark")) {
                int idx = Integer.parseInt(line[1]);
                myList = myList.unmark(idx);
                String res = String.format("""
                        %s
                        Alright! I've marked this task as not done yet:
                        \t%s
                        %s
                        """,
                        LINE,
                        myList.getTask(idx),
                        LINE);
                System.out.println(res);
            } else {
                myList = myList.add(new Task(String.join(" ", line)));
                String res = String.format("""
                        %s
                        added: %s
                        %s
                        """,
                        LINE,
                        String.join(" ", line),
                        LINE);
                System.out.println(res);
            }
        }
        sc.close();
    }
}