public class Doopies {
    private static final String LINE = "_".repeat(60);

    public static void main(String[] args) {
        String intro = String.format("""
                %s
                Hello! I'm Doopies
                What can I do for you?
                %s
                """,
                LINE,
                LINE);

        String end = String.format("""
                Bye. Hope to see you soon!
                %s
                """,
                LINE);

        System.out.println(intro + end);
    }
}