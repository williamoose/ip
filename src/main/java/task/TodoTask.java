package task;

public class TodoTask extends Task {
    
    /**
     * Call this constructor for raw inputs from the user.
     *
     * @param taskDescription String description of a task.
     */
    public TodoTask(String taskDescription) {
        super(taskDescription);
    }
    
    /**
     * Call this constructor when reading inputs from saved file.
     *
     * @param taskDescription String description of task.
     * @param isDone Boolean to represent whether a task is completed.
     */
    public TodoTask(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return "T" + " / " + super.convertToFileFormat();
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
