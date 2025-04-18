package main;

public class WorkTask extends Task {
    public WorkTask(String description) {
        super(description);
    }

    @Override
    public void displayTask() {
        System.out.println("Work Task: " + description);
    }
}