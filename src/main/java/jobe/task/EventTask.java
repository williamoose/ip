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
     * Creates an EventTask with the formatted arguments.
     *
     * @param taskDescription String description of task.
     * @param startDate Start date/time of event.
     * @param endDate End date/time of event.
     * @return A EventTask object with the corresponding formatted arguments.
     * @throws JobeException If formatting of start or end date/time fails.
     */
    public static EventTask createEventTask(String taskDescription, String startDate, String endDate) throws JobeException {
        String startDateString = StringUtils.splitStringAndRemoveFirstWord(startDate);
        String endDateString = StringUtils.splitStringAndRemoveFirstWord(endDate);
        
        String formattedStartDate = DateUtils.convertToDateTime(startDateString);
        String formattedEndDate = DateUtils.convertToDateTime(endDateString);
        
        assert formattedStartDate != null : "Start date should never be null";
        assert formattedEndDate != null : "End date should never be null";
        return new EventTask(taskDescription, formattedStartDate, formattedEndDate);
    }
    
    public EventTask(String taskDescription, String startDate, String endDate) throws JobeException {
        super(taskDescription);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    
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
