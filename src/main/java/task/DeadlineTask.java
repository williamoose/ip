package task;

import dateutils.DateUtils;
import exception.JobeException;
import stringutils.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    
    private String deadline;
    
    // Call this constructor for raw inputs by user
    public DeadlineTask(String taskDescription, String deadline) throws JobeException {
        super(taskDescription);
        String removedFirstWord = StringUtils.splitStringAndRemoveFirstWord(deadline);
        this.deadline = DateUtils.convertToDateTime(removedFirstWord);
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
