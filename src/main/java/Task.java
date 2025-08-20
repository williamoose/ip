public class Task {
  private boolean isDone;
  private String taskDescription;
  
  public Task(String taskDescription) {
    this.isDone = false;
    this.taskDescription = taskDescription;
  }
  
  public String toString() {
    return printStatus() + " " + this.taskDescription;
  }
  
  public String printStatus() {
    return isDone ? "[X]" : "[ ]";
  }
  
  public void setDone() {
    this.isDone = true;
  }
  
  public void setUndone() {
    this.isDone = false;
  }
}
