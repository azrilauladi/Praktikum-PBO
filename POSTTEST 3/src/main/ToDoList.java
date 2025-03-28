package main;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<ToDoTask> tasks;

    public ToDoList(String owner) {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        tasks.add(new ToDoTask(description));
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public List<ToDoTask> getTasks() {
        return tasks;
    }
}
