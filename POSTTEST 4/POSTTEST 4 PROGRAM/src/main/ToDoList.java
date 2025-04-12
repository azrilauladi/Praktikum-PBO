package main;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<Task> tasks;

    public ToDoList(String owner) {
        this.tasks = new ArrayList<>();
    }

    // Overloading addTask: menerima objek Task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Overloading addTask: menerima deskripsi tugas sebagai String
    public void addTask(String description) {
        tasks.add(new PersonalTask(description)); // Default ke PersonalTask
    }

    // Overloading addTask: menerima deskripsi dan prioritas
    public void addTask(String description, String priority) {
        switch (priority.toLowerCase()) {
            case "work":
                tasks.add(new WorkTask(description));
                break;
            case "urgent":
                tasks.add(new UrgentTask(description));
                break;
            default:
                tasks.add(new PersonalTask(description));
                break;
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}