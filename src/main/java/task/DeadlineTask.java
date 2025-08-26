package task;

import stringutils.StringUtils;

public class DeadlineTask extends Task {
    
    private String deadline;
    
    // Call this constructor for raw inputs by user
    public DeadlineTask(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = StringUtils.splitStringAndRemoveFirstWord(deadline);
    }
    
    // Call this constructor for parsed deadline inputs
    public DeadlineTask(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadline = deadline;
    }
    
    @Override
    public String convertToFileFormat() {
        return "D" + " / " + super.convertToFileFormat()
                + " / " + deadline;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
