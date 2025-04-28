package main;

public abstract class Task {
    protected String description;

    public Task(final String description) { // Parameter tidak boleh diubah
        this.description = description;
    }

    public final String getDescription() { // Method ini tidak boleh dioverride
        return description;
    }

    public abstract void displayTask();

    // Overloading displayTask: menerima parameter tambahan
    final public void displayTask(boolean showDetails) { // Parameter tidak boleh diubah
        if (showDetails) {
            System.out.println("Task: " + description + " [Details: Additional information]");
        } else {
            System.out.println("Task: " + description);
        }
    }
}