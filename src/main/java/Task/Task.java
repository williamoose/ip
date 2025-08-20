package Task;

public class Task {
  private boolean isDone;
  private String taskDescription;
  
  public Task(String taskDescription) {
    this.isDone = false;
    this.taskDescription = taskDescription;
  }
  
  public void setDone() {
    this.isDone = true;
  }
  
  public void setUndone() {
    this.isDone = false;
  }
  
  public String printStatus() {
    return isDone ? "[X]" : "[ ]";
  }
  
  public String toString() {
    return printStatus() + " " + this.taskDescription;
  }
}
