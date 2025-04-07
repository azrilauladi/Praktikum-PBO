package main;

public class UrgentTask extends Task {
    public UrgentTask(String description) {
        super(description);
    }

    @Override
    public void displayTask() {
        System.out.println("Urgent Task: " + description + " (This task is urgent!)");
    }
}