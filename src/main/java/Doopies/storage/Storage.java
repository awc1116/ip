package doopies.storage;

import doopies.exception.InvalidTaskTypeException;
import doopies.notebook.Deadline;
import doopies.notebook.Event;
import doopies.notebook.Notebook;
import doopies.notebook.Task;
import doopies.notebook.ToDo;
import doopies.util.TaskComparator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public Notebook load() throws IOException {
        if (Files.notExists(this.filePath)) {
            Files.createDirectories(this.filePath.getParent());
            Files.createFile(this.filePath);
        }

        List<Task> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            try {
                tasks.add(parseTask(line));
            } catch (InvalidTaskTypeException e) {
                System.err.println("Skipped invalid line: " + line);
            }
        }
        return new Notebook(tasks);
    }

    private Task parseTask(String line) throws InvalidTaskTypeException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        return switch (type) {
            case "T" -> new ToDo(description, isDone);
            case "D" -> new Deadline(description, isDone, parts[3]);
            case "E" -> new Event(description, isDone, parts[3], parts[4]);
            default -> throw new InvalidTaskTypeException("Invalid task type");
        };
    }

    public void save(Notebook notebook) throws IOException {
        Files.write(this.filePath, new ArrayList<String>());

        List<Task> tasks = new ArrayList<>(notebook.getAllTasks());
        tasks.sort(new TaskComparator());

        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(serializeTask(task));
        }
        Files.write(this.filePath, lines);
    }

    private String serializeTask(Task task) {
        if (task instanceof ToDo) {
            return String.format("T | %d | %s", task.isDone() ? 1 : 0, task.getTask());
        } else if (task instanceof Deadline) {
            return String.format("D | %d | %s | %s", task.isDone() ? 1 : 0, task.getTask(),
                    ((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            return String.format("E | %d | %s | %s | %s", task.isDone() ? 1 : 0, task.getTask(),
                    ((Event) task).getStart(), ((Event) task).getEnd());
        }
        return "";
    }
}
