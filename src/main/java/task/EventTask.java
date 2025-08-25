package task;

import stringutils.StringUtils;

public class EventTask extends Task {
    
    private String fromDate;
    private String toDate;
    
    public EventTask(String taskDescription, String fromDate, String toDate) {
        super(taskDescription);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    
    public String toString() {
        String fromDate = StringUtils.removeCommandWord(this.fromDate.split(" "));
        String toDate = StringUtils.removeCommandWord(this.toDate.split(" "));
        return "  [E]" + super.toString() + "(from: " + fromDate + " to: " + toDate + ")";
    }
}
