package jobe.task;

/**
 * Represents one singular jobe.task.
 */
public class Task {
    private boolean isDone;
    private String taskDescription;
    
    /**
     * Constructor if tasks isDone status is set to default which is false.
     *
     * @param taskDescription String description of jobe.task.
     */
    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }
    
    /**
     * Constructor if tasks isDone status needs to be manually set.
     *
     * @param taskDescription String description of jobe.task.
     * @param isDone Boolean to represent whether a jobe.task is done.
     */
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
    
    /**
     * Prints the status of the jobe.task depending on whether it is done.
     *
     * @return String representation of jobe.task's done status.
     */
    public String printStatus() {
        return isDone ? "[X]" : "[ ]";
    }
    
    public String getTaskDescription() {
        return this.taskDescription;
    }
    
    /**
     * Converts the jobe.task into a savable format.
     *
     * @return a String representation of the jobe.task to be saved to a file.
     */
    public String convertToFileFormat() {
        String completionStatus = isDone ? "[X]" : "[ ]";
        return completionStatus + " / " + taskDescription;
    }
    
    @Override
    public String toString() {
        return printStatus() + " " + this.taskDescription;
    }
}
