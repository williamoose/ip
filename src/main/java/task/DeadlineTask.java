package task;

import dateutils.DateUtils;
import exception.JobeException;
import stringutils.StringUtils;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    
    private String deadline;
    
    /**
     * Call this constructor for raw inputs from the user.
     *
     * @param taskDescription String description of task.
     * @param deadline Deadline of task.
     * @throws JobeException If formatting of date fails.
     */
    public DeadlineTask(String taskDescription, String deadline) throws JobeException {
        super(taskDescription);
        String removedFirstWord = StringUtils.splitStringAndRemoveFirstWord(deadline);
        this.deadline = DateUtils.convertToDateTime(removedFirstWord);
    }
    
    /**
     * Call this constructor when reading inputs from saved file.
     *
     * @param taskDescription String description of task.
     * @param deadline Deadline of task.
     * @param isDone Boolean representing whether a task is complete.
     */
    public DeadlineTask(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadline = deadline;
    }
    
    /**
     * {@inheritDoc}
     */
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
