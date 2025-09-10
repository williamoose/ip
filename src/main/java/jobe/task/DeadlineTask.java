package jobe.task;

import jobe.dateutils.DateUtils;
import jobe.exception.JobeException;
import jobe.stringutils.StringUtils;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    
    private String deadline;
    
    /**
     * Creates a DeadlineTask object with formatted arguments.
     *
     * @param taskDescription String description of task.
     * @param deadline Deadline of task.
     * @return A DeadlineTask with the corresponding formatted arguments.
     * @throws JobeException If formatting of date fails.
     */
    public static DeadlineTask createDeadlineTask(String taskDescription, String deadline) throws JobeException {
        String removedFirstWord = StringUtils.splitStringAndRemoveFirstWord(deadline);
        
        String formattedDeadline = DateUtils.convertToDateTime(removedFirstWord);
        assert formattedDeadline != null : "Deadline should never be null";
        
        return new DeadlineTask(taskDescription, formattedDeadline);
    }
    
    public DeadlineTask(String taskDescription, String deadline) throws JobeException {
        super(taskDescription);
        this.deadline = deadline;
    }
    
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
