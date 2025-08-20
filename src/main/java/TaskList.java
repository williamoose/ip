import java.util.ArrayList;
import java.util.List;

public class TaskList {
  
  private List<Task> listOfTasks;
  
  public TaskList() {
    this.listOfTasks = new ArrayList<>();
  }
  
  public void addTask(Task task) {
    this.listOfTasks.add(task);
  }
  
  public Task getTask(int index) {
    return this.listOfTasks.get(index);
  }
  
  public String toString() {
    StringBuilder str = new StringBuilder();
    int count = 1;
    for (Task task : listOfTasks) {
      str.append(count).append(". ").append(task).append("\n");
      count++;
    }
    
    return str.toString();
  }
}
