import java.util.*;
import java.io.*;

public class TaskManager {

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        loadTasks();

        while (true) {
            System.out.println("\n--- Student Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> deleteTask();
                case 4 -> completeTask();
                case 5 -> {
                    saveTasks();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();

        System.out.print("Enter deadline: ");
        String deadline = sc.nextLine();

        tasks.add(new Task(title, deadline, false));
        System.out.println("Task added successfully!");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(
                (i + 1) + ". " + t.title +
                " | Deadline: " + t.deadline +
                " | Status: " + (t.isCompleted ? "Completed" : "Pending")
            );
        }
    }

    static void deleteTask() {
        viewTasks();
        System.out.print("Enter task number to delete: ");
        int index = sc.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static void completeTask() {
        viewTasks();
        System.out.print("Enter task number to mark completed: ");
        int index = sc.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).isCompleted = true;
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static void saveTasks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                pw.println(t);
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                tasks.add(Task.fromString(fileScanner.nextLine()));
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }
    }
}
