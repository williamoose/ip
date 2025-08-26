package task;

import stringutils.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    
    private String deadlineDateTime;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("d/MM/yyyy HHmm");
    
    // Call this constructor for raw inputs by user
    public DeadlineTask(String taskDescription, String deadline) {
        super(taskDescription);
        String removedFirstWord = StringUtils.splitStringAndRemoveFirstWord(deadline);
        this.deadlineDateTime = convertToDateTime(removedFirstWord);
    }
    
    // Call this constructor for parsed deadline inputs
    public DeadlineTask(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadlineDateTime = deadline;
    }
    
    @Override
    public String convertToFileFormat() {
        return "D" + " / " + super.convertToFileFormat()
                + " / " + deadlineDateTime;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadlineDateTime + ")";
    }
    
    public String convertToDateTime(String input) {
        LocalDateTime date = LocalDateTime.parse(input, DATE_TIME_FORMATTER);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }
}
