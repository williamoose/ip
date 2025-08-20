package Task;

public class DeadlineTask extends Task {
  
  private String deadline;
  
  public DeadlineTask(String taskDesc, String deadline) {
    super(taskDesc);
    this.deadline = deadline;
  }
  
  @Override
  public String toString() {
    String deadline = this.deadline.split(" ")[1];
    return "  [D]" + super.toString() + "(by: " + deadline + ")";
  }
}
