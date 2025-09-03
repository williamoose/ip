package jobe.task;

import jobe.dateutils.DateUtils;
import jobe.exception.JobeException;
import jobe.stringutils.StringUtils;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    
    private String fromDate;
    private String toDate;
    
    /**
     * Call this constructor for raw inputs from the user.
     *
     * @param taskDescription String description of task.
     * @param fromDate Start date/time of event.
     * @param toDate End date/time of event.
     * @throws JobeException If formatting of start or end date/time fails.
     */
    public EventTask(String taskDescription, String fromDate, String toDate) throws JobeException {
        super(taskDescription);
        String from = StringUtils.splitStringAndRemoveFirstWord(fromDate);
        String to = StringUtils.splitStringAndRemoveFirstWord(toDate);
        this.fromDate = DateUtils.convertToDateTime(from);
        this.toDate = DateUtils.convertToDateTime(to);
    }
    
    /**
     * Call this constructor when reading inputs from saved files.
     *
     * @param taskDescription String description of task.
     * @param fromDate Start date/time of event.
     * @param toDate End date/time of event.
     * @param isDone Boolean to represent whether a task is completed.
     */
    public EventTask(String taskDescription, String fromDate, String toDate, boolean isDone) {
        super(taskDescription, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return "E" + " / " + super.convertToFileFormat() + " / " + fromDate + " / " + toDate;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + fromDate + " -> " + toDate + ")";
    }
}
