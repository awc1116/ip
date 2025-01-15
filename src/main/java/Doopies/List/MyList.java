package Doopies.List;
import java.util.List;
import java.util.ArrayList;

public class MyList {
    private final List<String> list;
    private static final String LINE = "_".repeat(60);

    public MyList() {
        this.list = List.<String>of();
    }

    private MyList(List<String> list) {
        this.list = list;
    }

    public MyList add(String task) {
        List<String> newList = new ArrayList<String>(this.list);
        newList.add(task);
        return new MyList(newList.stream().toList());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, this.list.get(i));
            str.append(temp);
        }
        return String.format("""
                %s
                %s%s
                """,
                LINE,
                str,
                LINE);

    }
}
