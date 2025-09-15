package jobe.task;

import jobe.Jobe;
import jobe.exception.JobeException;

/**
 * Represents one singular task.
 */
public class Task {
    private boolean isDone;
    private String taskDescription;
    
    /**
     * Constructor if tasks isDone status is set to default which is false.
     *
     * @param taskDescription String description of task.
     */
    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }
    
    /**
     * Constructor if tasks isDone status needs to be manually set.
     *
     * @param taskDescription String description of task.
     * @param isDone          Boolean to represent whether a task is done.
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
     * Prints the status of the task depending on whether it is done.
     *
     * @return String representation of task's done status.
     */
    public String printStatus() {
        return isDone ? "[X]" : "[ ]";
    }
    
    public String getTaskDescription() {
        return this.taskDescription;
    }
    
    /**
     * Checks whether
     * @param task
     * @param taskList
     * @throws JobeException
     */
    public void checkDuplicates(Task task, TaskList taskList) throws JobeException {
        boolean isDuplicate = taskList
          .toStream()
          .anyMatch(existing -> existing.isDuplicate(task));
        
        if (!isDuplicate) {
            return;
        }
        
        task.throwDuplicateTaskException();
    }
    
    public boolean isDuplicate(Task task) {
        if (this == task) {
            return true;
        }
        
        boolean isNullOrIsDifferentClass = task == null || this.getClass() != task.getClass();
        if (isNullOrIsDifferentClass) {
            return false;
        }
        
        return this.taskDescription.trim().equals(task.taskDescription.trim());
    }
    
    public void throwDuplicateTaskException() throws JobeException {
        throw new JobeException("OOPS!!!! A Todo task with the same description already exists!\n"
                + "Please create a different task!");
    }
    
    /**
     * Converts the task into a savable format.
     *
     * @return a String representation of the task to be saved to a file.
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
