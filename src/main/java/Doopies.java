import java.util.Arrays;
import java.util.Scanner;

import Doopies.Exception.UnknownCommandException;
import Doopies.notebook.*;

public class Doopies {
    private static final String LINE = "_".repeat(60);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Notebook noteBook = new Notebook();

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
            try {
                String[] line = sc.nextLine().split("/");
                String[] cmd = line[0].split(" ");

                if (cmd[0].equalsIgnoreCase("bye")) {
                    System.out.println(end);
                    break;
                } else if (cmd[0].equalsIgnoreCase("list")) {
                    System.out.println(noteBook);
                } else if (cmd[0].equalsIgnoreCase("mark")) {
                    int idx = Integer.parseInt(cmd[1]);
                    noteBook = noteBook.mark(idx);
                    String res = String.format("""
                        %s
                        Alright! I've marked this task as done:
                        \t%s
                        %s
                        """,
                            LINE,
                            noteBook.getTask(idx),
                            LINE);
                    System.out.println(res);
                } else if (cmd[0].equalsIgnoreCase("unmark")) {
                    int idx = Integer.parseInt(cmd[1]);
                    noteBook = noteBook.unmark(idx);
                    String res = String.format("""
                        %s
                        Alright! I've marked this task as not done yet:
                        \t%s
                        %s
                        """,
                            LINE,
                            noteBook.getTask(idx),
                            LINE);
                    System.out.println(res);
                } else {
                    String instruction = String.join(" ",
                            Arrays.copyOfRange(cmd, 1, cmd.length));
                    String task;
                    if (cmd[0].equalsIgnoreCase("todo")) {
                        ToDo todo = new ToDo(instruction);
                        noteBook = noteBook.add(todo);
                        task = todo.toString();
                    } else if (cmd[0].equalsIgnoreCase("deadline")) {
                        String[] temp = line[1].split(" ");
                        String dueDate = String.format("by: %s",
                                String.join(" ",
                                        Arrays.copyOfRange(temp, 1, temp.length)));
                        Deadline deadline = new Deadline(instruction, dueDate);
                        noteBook = noteBook.add(deadline);
                        task = deadline.toString();
                    } else if (cmd[0].equalsIgnoreCase("event")) {
                        String[] temp1 = line[1].split(" ");
                        String[] temp2 = line[2].split(" ");
                        String from = String.format("from: %s",
                                String.join(" ",
                                        Arrays.copyOfRange(temp1, 1, temp1.length)));
                        String to = String.format("to: %s",
                                String.join(" ",
                                        Arrays.copyOfRange(temp2, 1, temp2.length)));
                        Event event = new Event(instruction, from, to);
                        noteBook = noteBook.add(event);
                        task = event.toString();
                    } else {
                        throw new UnknownCommandException("OOPS!!! I'm sorry, " +
                                "but I don't know what that means :-(");
                    }
                    String res = String.format("""
                        %s
                        Got it. I've added this task:
                        \t%s
                        Not you have %d tasks in the list.
                        %s
                        """,
                            LINE,
                            task,
                            noteBook.size(),
                            LINE);
                    System.out.println(res);
                }
            } catch(UnknownCommandException e) {
                System.out.println(ErrorMessage(e.getMessage()));
            }
        }
        sc.close();
    }

    private static String ErrorMessage(String msg) {
        return String.format("""
                %s
                %s
                %s
                """,
                LINE,
                msg,
                LINE);
    }
}