package task;

import dateutils.DateUtils;
import exception.JobeException;
import stringutils.StringUtils;

public class EventTask extends Task {
    
    private String fromDate;
    private String toDate;
    
    // Call this constructor for raw inputs by user
    public EventTask(String taskDescription, String fromDate, String toDate) throws JobeException {
        super(taskDescription);
        String from = StringUtils.splitStringAndRemoveFirstWord(fromDate);
        String to = StringUtils.splitStringAndRemoveFirstWord(toDate);
        this.fromDate = DateUtils.convertToDateTime(from);
        this.toDate = DateUtils.convertToDateTime(to);
    }
    
    // Call this constructor for parsed fromDate and toDate inputs
    public EventTask(String taskDescription, String fromDate, String toDate, boolean isDone) {
        super(taskDescription, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    
    @Override
    public String convertToFileFormat() {
        return "E" + " / " + super.convertToFileFormat() + " / " + fromDate + " / " + toDate;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + fromDate + " -> " + toDate + ")";
    }
}
