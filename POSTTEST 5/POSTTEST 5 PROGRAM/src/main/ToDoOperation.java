package main;

public interface ToDoOperation {
    void addTask(String description);
    void removeTask(int index);
    void displayTasks();
}