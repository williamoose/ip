package jobe.task;

import jobe.dateutils.DateUtils;
import jobe.exception.JobeException;
import jobe.stringutils.StringUtils;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    
    private String startDate;
    private String endDate;
    
    /**
     * Call this constructor for raw inputs from the user.
     *
     * @param taskDescription String description of task.
     * @param startDate Start date/time of event.
     * @param endDate End date/time of event.
     * @throws JobeException If formatting of start or end date/time fails.
     */
    public EventTask(String taskDescription, String startDate, String endDate) throws JobeException {
        super(taskDescription);
<<<<<<< Updated upstream
        String from = StringUtils.splitStringAndRemoveFirstWord(fromDate);
        String to = StringUtils.splitStringAndRemoveFirstWord(toDate);
        this.fromDate = DateUtils.convertToDateTime(from);
        this.toDate = DateUtils.convertToDateTime(to);
=======
        
        String from = StringUtils.splitStringAndRemoveFirstWord(startDate);
        String to = StringUtils.splitStringAndRemoveFirstWord(endDate);
        
        this.startDate = DateUtils.convertToDateTime(from);
        this.endDate = DateUtils.convertToDateTime(to);
        
        assert startDate != null : "Start date should never be null";
        assert endDate != null : "End date should never be null";
>>>>>>> Stashed changes
    }
    
    /**
     * Call this constructor when reading inputs from saved files.
     *
     * @param taskDescription String description of task.
     * @param startDate Start date/time of event.
     * @param endDate End date/time of event.
     * @param isDone Boolean to represent whether a task is completed.
     */
    public EventTask(String taskDescription, String startDate, String endDate, boolean isDone) {
        super(taskDescription, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return "E" + " / " + super.convertToFileFormat() + " / " + startDate + " / " + endDate;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startDate + " -> " + endDate + ")";
    }
}
