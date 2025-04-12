package main;

public abstract class Task {
    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void displayTask();

    // Overloading displayTask: menerima parameter tambahan
    public void displayTask(boolean showDetails) {
        if (showDetails) {
            System.out.println("Task: " + description + " [Details: Additional information]");
        } else {
            System.out.println("Task: " + description);
        }
    }
}