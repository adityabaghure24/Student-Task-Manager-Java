public class Task {
    String title;
    String deadline;
    boolean isCompleted;

    public Task(String title, String deadline, boolean isCompleted) {
        this.title = title;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return title + "," + deadline + "," + isCompleted;
    }

    public static Task fromString(String line) {
        String[] parts = line.split(",");
        return new Task(
                parts[0],
                parts[1],
                Boolean.parseBoolean(parts[2])
        );
    }
}
