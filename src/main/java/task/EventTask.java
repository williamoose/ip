package task;

import stringutils.StringUtils;

public class EventTask extends Task {
    
    private String fromDate;
    private String toDate;
    
    // Call this constructor for raw inputs by user
    public EventTask(String taskDescription, String fromDate, String toDate) {
        super(taskDescription);
        this.fromDate = StringUtils.splitStringAndRemoveFirstWord(fromDate);
        this.toDate = StringUtils.splitStringAndRemoveFirstWord(toDate);
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
        return "[E]" + super.toString() + "(from: " + fromDate + " to: " + toDate + ")";
    }
}
