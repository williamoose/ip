package task;

public class Task {
    private boolean isDone;
    private String taskDescription;
    
    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }
    
    public Task(String taskDescription, boolean isDone) {
        this.isDone = isDone;
        this.taskDescription = taskDescription;
    }
    
    public void setDone() {
        this.isDone = true;
    }
    
    public void setUndone() {
        this.isDone = false;
    }
    
    public String printStatus() {
        return isDone ? "[X]" : "[ ]";
    }
    
    public String convertToFileFormat() {
        String completionStatus = isDone ? "[X]" : "[ ]";
        return completionStatus + " / " + taskDescription;
    }
    
    public String toString() {
        return printStatus() + " " + this.taskDescription;
    }
}
