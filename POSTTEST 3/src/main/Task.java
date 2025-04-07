package main;

public abstract class Task { // Tambahkan 'public'
    protected String description;
    
    public Task(String description) {
        this.description = description;
    }
    
    public String getDescription() { // Tambahkan getter untuk description
        return description;
    }
    
    public abstract void displayTask();
}