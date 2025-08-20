package task;

import java.util.Arrays;

public class DeadlineTask extends Task {
  
  private String deadline;
  
  public DeadlineTask(String taskDesc, String deadline) {
    super(taskDesc);
    this.deadline = deadline;
  }
  
  @Override
  public String toString() {
    String[] deadlineSplit = this.deadline.split(" ");
    String[] deadlineSliced = Arrays.copyOfRange(deadlineSplit, 1, deadlineSplit.length);
    String deadline = String.join(" ", deadlineSliced);
    return "  [D]" + super.toString() + "(by: " + deadline + ")";
  }
}
