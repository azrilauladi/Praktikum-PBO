package main;

public class PersonalTask extends Task {
    public PersonalTask(String description) {
        super(description);
    }

    @Override
    public void displayTask() {
        System.out.println("Personal Task: " + description);
    }
}